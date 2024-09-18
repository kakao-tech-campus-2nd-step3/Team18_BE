package team18.team18_be.auth.dto;

public record OAuthJwtResponse(
    String accessToken,
    String refreshToken
) {

}
