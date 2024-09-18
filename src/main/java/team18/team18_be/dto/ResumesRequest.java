package team18.team18_be.dto;

public record ResumesRequest(
        String resumeId,
        String applicantName,
        String address,
        String tel,
        String preferredWorkLocation,
        String preferredIndustry,
        String preferredWorkingHours,
        String selfIntroduction
) {
}
