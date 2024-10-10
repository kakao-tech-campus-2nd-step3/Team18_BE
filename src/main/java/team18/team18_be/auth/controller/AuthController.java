package team18.team18_be.auth.controller;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team18.team18_be.auth.dto.request.CodeRequest;
import team18.team18_be.auth.dto.request.UserTypeRequest;
import team18.team18_be.auth.dto.response.LoginResponse;
import team18.team18_be.auth.dto.response.OAuthJwtResponse;
import team18.team18_be.auth.dto.response.UserTypeResponse;
import team18.team18_be.auth.entity.User;
import team18.team18_be.auth.service.AuthService;
import team18.team18_be.config.resolver.LoginUser;

@Tag(name = "인증/인가", description = "인증/인가 관련 API")
@RestController
@RequestMapping("/api")
public class AuthController {

  private static final String GOOGLE_TOKEN_URI = "https://oauth2.googleapis.com/token";

  private static final String GOOGLE_USER_INFO_URI = "https://www.googleapis.com/userinfo/v2/me";

  private final AuthService authService;

  public AuthController(AuthService authService) {
    this.authService = authService;
  }

  @ApiResponse(responseCode = "200", description = "성공", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserTypeResponse.class)))
  @PostMapping("/oauth")
  public ResponseEntity<UserTypeResponse> login(@RequestBody CodeRequest codeRequest) {
    OAuthJwtResponse oAuthJwtResponse = authService.getOAuthToken(codeRequest,
        GOOGLE_TOKEN_URI);
    LoginResponse loginResponse = authService.registerOAuth(oAuthJwtResponse,
        GOOGLE_USER_INFO_URI);

    HttpHeaders headers = new HttpHeaders();
    headers.setBearerAuth(loginResponse.accessToken());

    UserTypeResponse userTypeResponse = new UserTypeResponse(loginResponse.type());

    return new ResponseEntity<>(userTypeResponse, headers, HttpStatus.OK);
  }

  @ApiResponse(responseCode = "200", description = "성공")
  @PostMapping("/register")
  public ResponseEntity<Void> registerUserType(@Valid @RequestBody UserTypeRequest userTypeRequest,
      @LoginUser User user) {
    authService.registerUserType(userTypeRequest, user);
    return ResponseEntity.status(HttpStatus.OK).build();
  }
}
