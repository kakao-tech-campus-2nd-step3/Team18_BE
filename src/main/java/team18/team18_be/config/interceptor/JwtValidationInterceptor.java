package team18.team18_be.config.interceptor;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.NoSuchElementException;
import java.util.Set;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import team18.team18_be.auth.repository.AuthRepository;
import team18.team18_be.exception.JwtInvalidException;

public class JwtValidationInterceptor implements HandlerInterceptor {

  private final Set<String> allowedMethods;
  private final AuthRepository authRepository;
  @Value("${jwt.header}")
  private String AUTHORIZATION;
  @Value("${jwt.type}")
  private String JWT_TYPE;
  @Value("${jwt.secret}")
  private String JWT_SECRET_KEY;

  public JwtValidationInterceptor(AuthRepository authRepository) {
    this.authRepository = authRepository;
    this.allowedMethods = Set.of("POST",
        "GET",
        "PUT",
        "DELETE",
        "PATCH",
        "HEAD",
        "OPTIONS",
        "CONNECT",
        "TRACE");
  }

  public JwtValidationInterceptor(AuthRepository authRepository, Set<String> allowedMethods) {
    this.authRepository = authRepository;
    this.allowedMethods = allowedMethods;
  }

  @Override
  public boolean preHandle(HttpServletRequest request,
      HttpServletResponse response,
      Object handler) {

    if (!allowedMethods.contains(request.getMethod().toUpperCase())) {
      return true;
    }

    String accessToken = getAccessToken(request.getHeaders(AUTHORIZATION));

    if (accessToken.isEmpty()) {
      throw new JwtInvalidException("요청에 액세스 토큰이 존재하지 않습니다.");
    }

    try {
      return setUserIdInRequest(accessToken, request);
    } catch (Exception e) {
      throw new JwtInvalidException("액세스 토큰이 유효하지 않습니다.");
    }
  }

  private String getAccessToken(Enumeration<String> headers) {
    String accessToken = "";

    while (headers.hasMoreElements()) {
      String value = headers.nextElement();

      if (value.toLowerCase().startsWith(JWT_TYPE.toLowerCase())) {
        accessToken = value.substring(JWT_TYPE.length()).trim();
        break;
      }
    }

    return accessToken;
  }

  private boolean setUserIdInRequest(String accessToken,
      HttpServletRequest request) {
    String encodedSecretKey = Encoders.BASE64.encode(
        JWT_SECRET_KEY.getBytes(StandardCharsets.UTF_8));

    byte[] keyBytes = Decoders.BASE64URL.decode(encodedSecretKey);

    SecretKey secretKey = Keys.hmacShaKeyFor(keyBytes);

    Jws<Claims> claims = Jwts.parser()
        .verifyWith(secretKey)
        .build()
        .parseSignedClaims(accessToken);

    Long userId = claims.getPayload().get("userId", Long.class);

    validateUserExistence(userId);
    request.setAttribute("userId", userId);

    return true;
  }

  private void validateUserExistence(Long userId) {
    if (!authRepository.existsById(userId)) {
      throw new NoSuchElementException("회원 정보가 존재하지 않습니다.");
    }
  }
}
