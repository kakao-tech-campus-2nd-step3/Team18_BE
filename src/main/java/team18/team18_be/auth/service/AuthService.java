package team18.team18_be.auth.service;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URI;
import org.apache.logging.log4j.util.InternalException;
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
import team18.team18_be.auth.repository.AuthRepository;
import team18.team18_be.config.property.GoogleProperty;
import team18.team18_be.exception.OAuthLoginFailedException;

@Service
public class AuthService {

  private final AuthRepository authRepository;
  private final GoogleProperty googleProperty;
  private final RestClient restClient = RestClient.builder().build();

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

  private LinkedMultiValueMap<String, String> getRequestBody(CodeRequest codeRequest) {
    LinkedMultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
    requestBody.add("code", codeRequest.code());
    requestBody.add("client_id", googleProperty.clientId());
    requestBody.add("client_secret", googleProperty.clientSecret());
    requestBody.add("redirect_uri", googleProperty.redirectUri());
    requestBody.add("grant-type", googleProperty.grantType());
    return requestBody;
  }

  public LoginResponse registerOAuth(OAuthJwtResponse oAuthJwtResponse, String externalApiUri) {
    return null;
  }

  public void registerUserType(UserTypeRequest userTypeRequest, User user) {
    authRepository.save(user.updateUserType(userTypeRequest.type()));
  }

}
