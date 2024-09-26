package team18.team18_be.resume.entity;

public class Resume {
    private Long resumeId;
    private String applicantName;
    private String address;
    private String phoneNumber;
    private String career;
    private String korean;
    private String selfIntroduction;

    public Resume(Long resumeId, String applicantName, String address, String phoneNumber, String career, String korean, String selfIntroduction) {
        this.resumeId = resumeId;
        this.applicantName = applicantName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.career = career;
        this.korean = korean;
        this.selfIntroduction = selfIntroduction;
    }
}
