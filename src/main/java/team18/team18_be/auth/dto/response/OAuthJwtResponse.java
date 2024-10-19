package team18.team18_be.auth.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record OAuthJwtResponse(
    @JsonProperty("access_token") String accessToken
) {

}
