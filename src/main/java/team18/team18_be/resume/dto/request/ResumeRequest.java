package team18.team18_be.resume.dto.request;

public record ResumeRequest(
    String applicantName,
    String address,
    String phoneNumber,
    String career,
    String korean,
    String selfIntroduction
) {

}
