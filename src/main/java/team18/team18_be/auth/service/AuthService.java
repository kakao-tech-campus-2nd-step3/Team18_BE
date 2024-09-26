package team18.team18_be.auth.service;


import org.springframework.stereotype.Service;
import team18.team18_be.auth.dto.request.MemberIdRequest;
import team18.team18_be.auth.dto.request.UserTypeRequest;
import team18.team18_be.auth.dto.response.LoginResponse;
import team18.team18_be.auth.dto.response.OAuthJwtResponse;
import team18.team18_be.auth.repository.AuthRepository;

@Service
public class AuthService {

  private final AuthRepository authRepository;

  public AuthService(AuthRepository authRepository) {
    this.authRepository = authRepository;
  }

  public OAuthJwtResponse getOAuthToken(String code, String externalApiUrl) {
    return null;
  }

  public LoginResponse registerOAuth(OAuthJwtResponse oAuthJwtResponse, String externalApiUrl) {
    return null;
  }

  public void registerUserType(UserTypeRequest userTypeRequest, MemberIdRequest memberIdRequest) {

  }
}
