package team18.team18_be.auth.dto.response;

public record JwtResponse(
    String refreshToken,
    String accessToken
) {

}
