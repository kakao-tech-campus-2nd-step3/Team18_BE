package team18.team18_be.recruitment.dto.response;

public record RecruitmentSummationResponse(
    Long recruitmentId,
    String title,
    String companyName,
    String salary,
    String area
) {

}
