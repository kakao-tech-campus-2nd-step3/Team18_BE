package team18.team18_be.auth.controller;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;

import java.net.URI;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team18.team18_be.auth.dto.request.ClientIdRequest;
import team18.team18_be.auth.dto.request.MemberIdRequest;
import team18.team18_be.auth.dto.request.UserTypeRequest;
import team18.team18_be.auth.dto.response.LoginResponse;
import team18.team18_be.auth.dto.response.OAuthJwtResponse;
import team18.team18_be.auth.dto.response.UserTypeResponse;
import team18.team18_be.auth.service.AuthService;

@Tag(name = "인증/인가", description = "인증/인가 관련 API")
@RestController
@RequestMapping("/api")
public class AuthController {

    private static final String TOKEN_REDIRECT_URL = "?";

    private static final String GOOGLE_AUTH_TOKEN_URL = "https://oauth2.googleapis.com/token";

    private static final String GOOGLE_USER_INFO_URL = "https://www.googleapis.com/userinfo/v2/me";

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @ApiResponse(responseCode = "301", description = "성공 (리다이렉트)", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserTypeResponse.class)))
    @PostMapping("/oauth")
    public ResponseEntity<UserTypeResponse> login(@RequestBody ClientIdRequest clientIdRequest) {
        OAuthJwtResponse oAuthJwtResponse = authService.getOAuthToken(clientIdRequest.code(),
                GOOGLE_AUTH_TOKEN_URL);
        LoginResponse loginResponse = authService.registerOAuth(oAuthJwtResponse,
                GOOGLE_USER_INFO_URL);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(TOKEN_REDIRECT_URL));
        headers.setBearerAuth(loginResponse.accessToken());

        UserTypeResponse userTypeResponse = new UserTypeResponse(loginResponse.type());

        return new ResponseEntity<>(userTypeResponse, headers, HttpStatus.MOVED_PERMANENTLY);
    }

    @ApiResponse(responseCode = "200", description = "성공")
    @PostMapping("/register")
    public ResponseEntity<Void> registerUserType(@RequestBody UserTypeRequest userTypeRequest,
                                                 HttpServletRequest request) {
        MemberIdRequest memberIdRequest = getLoginMember(request);
        authService.registerUserType(userTypeRequest, memberIdRequest);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    private MemberIdRequest getLoginMember(HttpServletRequest request) {
        return new MemberIdRequest((Long) request.getAttribute("id"));
    }
}
