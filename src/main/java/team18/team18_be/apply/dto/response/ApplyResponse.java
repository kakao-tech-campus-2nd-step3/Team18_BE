package team18.team18_be.apply.dto.response;

public record ApplyResponse(
    Long userId,
    String name,
    String motivation,
    Long resumeId
) {

}
