package team18.team18_be.resume.dto;

public record ResumeRequest(
        String resumeId,
        String applicantName,
        String address,
        String phoneNumber,
        String career,
        String korean,
        String selfIntroduction
) {
}
