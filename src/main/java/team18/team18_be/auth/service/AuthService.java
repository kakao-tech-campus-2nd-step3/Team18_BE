package team18.team18_be.auth.service;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.NoSuchElementException;
import javax.crypto.SecretKey;
import org.apache.logging.log4j.util.InternalException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestClient;
import team18.team18_be.auth.dto.request.CodeRequest;
import team18.team18_be.auth.dto.request.UserTypeRequest;
import team18.team18_be.auth.dto.response.LoginResponse;
import team18.team18_be.auth.dto.response.OAuthJwtResponse;
import team18.team18_be.auth.entity.User;
import team18.team18_be.auth.entity.UserType;
import team18.team18_be.auth.repository.AuthRepository;
import team18.team18_be.config.property.GoogleProperty;
import team18.team18_be.exception.OAuthLoginFailedException;

@Service
public class AuthService {

  private final AuthRepository authRepository;
  private final GoogleProperty googleProperty;
  private final RestClient restClient = RestClient.builder().build();

  @Value("${jwt.secret}")
  private String SECRET_KEY;

  public AuthService(AuthRepository authRepository, GoogleProperty googleProperty) {
    this.authRepository = authRepository;
    this.googleProperty = googleProperty;
  }

  public OAuthJwtResponse getOAuthToken(CodeRequest codeRequest, String externalApiUri) {
    LinkedMultiValueMap<String, String> requestBody = getRequestBody(codeRequest);

    ResponseEntity<String> response = restClient.post()
        .uri(URI.create(externalApiUri))
        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        .body(requestBody)
        .retrieve()
        .onStatus(HttpStatusCode::is4xxClientError, ((req, res) -> {
          throw new OAuthLoginFailedException("구글 토큰 발급 관련 에러가 발생하였습니다.");
        }))
        .toEntity(String.class);

    try {
      ObjectMapper objectMapper = new ObjectMapper();
      JsonNode rootNode = objectMapper.readTree(response.getBody());
      String accessToken = rootNode.path("access_token").asText();
      return new OAuthJwtResponse(accessToken);
    } catch (Exception e) {
      throw new InternalException("OAuth 로그인 진행 중 예기치 못한 오류가 발생하였습니다.");
    }
  }

  public LoginResponse registerOAuth(OAuthJwtResponse oAuthJwtResponse, String externalApiUri) {
    ResponseEntity<String> response = restClient.get()
        .uri(URI.create(externalApiUri))
        .header("Authorization", "Bearer " + oAuthJwtResponse.accessToken())
        .retrieve()
        .onStatus(HttpStatusCode::is4xxClientError, ((req, res) -> {
          throw new OAuthLoginFailedException("구글 유저 정보 조회 중 에러가 발생하였습니다.");
        }))
        .toEntity(String.class);

    try {
      ObjectMapper objectMapper = new ObjectMapper();
      JsonNode rootNode = objectMapper.readTree(response.getBody());
      String userEmail = rootNode.path("email").asText();

      if (!authRepository.existsByEmail(userEmail)) {
        String userName = rootNode.path("name").asText();
        authRepository.save(new User(userName, userEmail, UserType.FIRST.getUserType()));
      }

      String profileImage = rootNode.path("picture").asText();
      return getLoginResponse(userEmail, profileImage);
    } catch (Exception e) {
      throw new InternalException("OAuth 로그인 진행 중 예기치 못한 오류가 발생하였습니다.");
    }
  }

  public void registerUserType(UserTypeRequest userTypeRequest, User user) {
    authRepository.save(user.updateUserType(userTypeRequest.type()));
  }

  private LinkedMultiValueMap<String, String> getRequestBody(CodeRequest codeRequest) {
    LinkedMultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
    requestBody.add("code", codeRequest.code());
    requestBody.add("client_id", googleProperty.clientId());
    requestBody.add("client_secret", googleProperty.clientSecret());
    requestBody.add("redirect_uri", googleProperty.redirectUri());
    requestBody.add("grant_type", googleProperty.grantType());
    return requestBody;
  }

  private LoginResponse getLoginResponse(String userEmail,
      String profileImage) {
    User user = authRepository.findByEmail(userEmail)
        .orElseThrow(() -> new NoSuchElementException("회원 정보가 존재하지 않습니다."));
    String userType = user.getType();
    String accessToken = getAccessToken(user);
    return new LoginResponse(accessToken, userType, profileImage);
  }

  private String getAccessToken(User user) {
    byte[] keyBytes = SECRET_KEY.getBytes(StandardCharsets.UTF_8);
    SecretKey key = Keys.hmacShaKeyFor(keyBytes);
    return Jwts.builder()
        .claim("userId", user.getId())
        .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60L))
        .signWith(key)
        .compact();
  }
}
