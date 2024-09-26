package team18.team18_be.config.interceptor;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import team18.team18_be.auth.repository.AuthRepository;

public class JwtValidationInterceptor implements HandlerInterceptor {

    private final AuthRepository authRepository;
    @Value("${jwt.header}")
    private String AUTHORIZATION;
    @Value("${jwt.type}")
    private String JWT_TYPE;
    @Value("${jwt.secret}")
    private String JWT_SECRET_KEY;

    public JwtValidationInterceptor(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    @Override
    public boolean preHandle(HttpServletRequest request,
        HttpServletResponse response,
        Object handler) throws Exception {

        String accessToken = getAccessToken(request.getHeaders(AUTHORIZATION));

        if (accessToken.isEmpty()) {
            response.sendError(401, "요청에 액세스 토큰이 존재하지 않습니다.");
            return false;
        }

        try {
            return setUserIdInRequest(accessToken, request, response);
        } catch (Exception e) {
            response.sendError(401, "액세스 토큰이 유효하지 않습니다.");
            return false;
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
        HttpServletRequest request,
        HttpServletResponse response)
        throws IOException {
        String encodedSecretKey = Encoders.BASE64.encode(JWT_SECRET_KEY.getBytes(StandardCharsets.UTF_8));

        byte[] keyBytes = Decoders.BASE64URL.decode(encodedSecretKey);

        SecretKey secretKey = Keys.hmacShaKeyFor(keyBytes);

        Jws<Claims> claims = Jwts.parser()
            .verifyWith(secretKey)
            .build()
            .parseSignedClaims(accessToken);

        Long userId = claims.getPayload().get("userId", Long.class);

        boolean isValid = validateUserExistence(userId, response);
        if (isValid) {
            request.setAttribute("userId", userId);
        }
        return isValid;
    }

    private boolean validateUserExistence(Long userId, HttpServletResponse response)
        throws IOException {
        boolean isValid = true;
        if (!authRepository.existsById(userId)) {
            response.sendError(401, "유저 정보가 존재하지 않습니다.");
            isValid = false;
        }
        return isValid;
    }
}
