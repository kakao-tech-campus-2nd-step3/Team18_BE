package team18.team18_be.resume.dto.response;

public record ResumeResponse(
        Long resumeId,
        String applicantName,
        String address,
        String phoneNumber,
        String career,
        String korean,
        String selfIntroduction
) {
}
