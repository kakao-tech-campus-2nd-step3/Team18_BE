package team18.team18_be.auth.controller;

import jakarta.servlet.http.HttpServletRequest;
import java.net.URI;
import java.time.Duration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team18.team18_be.auth.dto.request.ClientIdRequest;
import team18.team18_be.auth.dto.response.JwtResponse;
import team18.team18_be.auth.dto.request.MemberIdRequest;
import team18.team18_be.auth.dto.response.OAuthJwtResponse;
import team18.team18_be.auth.dto.request.UserTypeRequest;
import team18.team18_be.auth.service.AuthService;

@RestController
@RequestMapping("/api")
public class AuthController {

    private static final String TOKEN_REDIRECT_URL = "?";

    private static final String GOOGLE_AUTH_TOKEN_URL = "https://oauth2.googleapis.com/token";

    private static final String GOOGLE_USER_INFO_URL = " https://www.googleapis.com/userinfo/v2/me";

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/oauth")
    public ResponseEntity<Void> login(@RequestBody ClientIdRequest clientIdRequest) {
        OAuthJwtResponse oAuthJwtResponse = authService.getOAuthToken(clientIdRequest.clientId(), GOOGLE_AUTH_TOKEN_URL);
        JwtResponse jwtResponse = authService.registerOAuth(oAuthJwtResponse, GOOGLE_USER_INFO_URL);

        ResponseCookie accessTokenCookie = getResponseCookie(jwtResponse.accessToken(), "accessToken", 1);

        ResponseCookie refreshTokenCookie = getResponseCookie(jwtResponse.refreshToken(),
            "refreshToken",
            14);

        HttpHeaders headers = getHttpHeaders(accessTokenCookie,
            refreshTokenCookie);

        return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
    }

    @PostMapping("/register")
    public ResponseEntity<Void> registerUserType(@RequestBody UserTypeRequest userTypeRequest, HttpServletRequest request) {
        MemberIdRequest memberIdRequest = getLoginMember(request);
        authService.registerUserType(userTypeRequest, memberIdRequest);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    private static HttpHeaders getHttpHeaders(ResponseCookie accessTokenCookie,
        ResponseCookie refreshTokenCookie) {
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(TOKEN_REDIRECT_URL));
        headers.add(HttpHeaders.SET_COOKIE, accessTokenCookie.toString());
        headers.add(HttpHeaders.SET_COOKIE, refreshTokenCookie.toString());
        return headers;
    }

    private static ResponseCookie getResponseCookie(String token, String tokenType, int expiredDay) {
        return ResponseCookie.from(tokenType, token)
            .httpOnly(true)
            .secure(true)
            .path("/")
            .maxAge(Duration.ofDays(expiredDay))
            .build();
    }

    private MemberIdRequest getLoginMember(HttpServletRequest request) {
        return new MemberIdRequest((Long)request.getAttribute("id"));
    }
}
