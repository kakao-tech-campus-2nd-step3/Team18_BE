package team18.team18_be.resume.entity;

public class Resume {
  private final Long resumeId;
  private final String applicantName;
  private final String address;
  private final String phoneNumber;
  private final String career;
  private final String korean;
  private final String selfIntroduction;

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
