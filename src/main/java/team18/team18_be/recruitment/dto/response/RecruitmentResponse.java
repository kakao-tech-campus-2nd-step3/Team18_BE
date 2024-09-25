package team18.team18_be.recruitment.dto.response;

public record RecruitmentResponse(
    String title,
    String companySize,
    String area,
    String requestedCareer,
    String detailedDescription,
    String majorBusiness,
    String eligibilityCriteria,
    String preferredConditions,
    String employerName
) {

}
