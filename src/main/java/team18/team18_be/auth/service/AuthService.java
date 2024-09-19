package team18.team18_be.auth.service;


import team18.team18_be.auth.dto.response.JwtResponse;
import team18.team18_be.auth.dto.request.MemberIdRequest;
import team18.team18_be.auth.dto.response.OAuthJwtResponse;
import team18.team18_be.auth.dto.request.UserTypeRequest;
import team18.team18_be.auth.repository.AuthRepository;

public class AuthService {
    private final AuthRepository authRepository;

    public AuthService(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    public OAuthJwtResponse getOAuthToken(String clientId, String externalApiUrl) {
        return null;
    }

    public JwtResponse registerOAuth(OAuthJwtResponse oAuthJwtResponse, String externalApiUrl) {
        return null;
    }

    public void registerUserType(UserTypeRequest userTypeRequest, MemberIdRequest memberIdRequest) {

    }
}
