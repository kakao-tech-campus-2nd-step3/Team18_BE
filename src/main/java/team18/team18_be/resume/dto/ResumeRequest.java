package team18.team18_be.resume.dto;

public record ResumeRequest(
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
