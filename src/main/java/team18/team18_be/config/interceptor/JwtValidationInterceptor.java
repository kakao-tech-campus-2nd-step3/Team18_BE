package team18.team18_be.config.interceptor;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.util.NoSuchElementException;
import java.util.Set;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import team18.team18_be.auth.repository.AuthRepository;
import team18.team18_be.exception.ErrorMessage;
import team18.team18_be.exception.JwtExpiredException;
import team18.team18_be.exception.JwtInvalidException;

public class JwtValidationInterceptor implements HandlerInterceptor {

  public static final String NOT_FOUND_ACCESS_TOKEN_ERROR_MESSAGE = "요청에 액세스 토큰이 존재하지 않습니다.";
  public static final String BEARER = "Bearer ";
  public static final String ACCESS_TOKEN_EXPIRED_ERROR_MESSAGE = "액세스 토큰이 만료되었습니다.";
  public static final String USER_ID = "userId";
  public static final String NOT_FOUND_USER_ERROR_MESSAGE = "회원 정보가 존재하지 않습니다.";
  private final Set<String> allowedMethods;
  private final AuthRepository authRepository;
  @Value("${jwt.header}")
  private String AUTHORIZATION;
  @Value("${jwt.secret}")
  private String JWT_SECRET_KEY;

  public JwtValidationInterceptor(AuthRepository authRepository) {
    this.authRepository = authRepository;
    this.allowedMethods = Set.of("POST",
        "GET",
        "PUT",
        "DELETE",
        "PATCH");
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

    String accessToken = request.getHeader(AUTHORIZATION);

    if (accessToken == null) {
      throw new JwtInvalidException(NOT_FOUND_ACCESS_TOKEN_ERROR_MESSAGE);
    }

    accessToken = accessToken.replaceFirst(BEARER, "");
    return setUserIdInRequest(accessToken, request);
  }

  private boolean setUserIdInRequest(String accessToken,
      HttpServletRequest request) {
    byte[] keyBytes = JWT_SECRET_KEY.getBytes(StandardCharsets.UTF_8);
    SecretKey secretKey = Keys.hmacShaKeyFor(keyBytes);
    Jws<Claims> claims;

    try {
      claims = Jwts.parser()
          .verifyWith(secretKey)
          .build()
          .parseSignedClaims(accessToken);
    } catch (ExpiredJwtException e) {
      throw new JwtExpiredException(ACCESS_TOKEN_EXPIRED_ERROR_MESSAGE);
    }

    Long userId = claims.getPayload().get(USER_ID, Long.class);

    validateUserExistence(userId);
    request.setAttribute(USER_ID, userId);

    return true;
  }

  private void validateUserExistence(Long userId) {
    if (!authRepository.existsById(userId)) {
      throw new NoSuchElementException(ErrorMessage.NOT_FOUND_USER.getErrorMessage());
    }
  }
}
