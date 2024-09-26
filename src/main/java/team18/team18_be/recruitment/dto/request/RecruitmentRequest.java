package team18.team18_be.recruitment.dto.request;

public record RecruitmentRequest(
        String title,
        String companySize,
        String area,
        String salary,
        String workDuration,
        String workType,
        String workHours,
        String requestedCareer,
        String majorBusiness,
        String eligibilityCriteria,
        String preferredConditions,
        String employerName,
        String detailedDescription
) {
}
