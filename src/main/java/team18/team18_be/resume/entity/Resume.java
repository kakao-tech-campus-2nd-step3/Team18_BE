package team18.team18_be.resume.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Resume {

  @Id
  private Long resumeId;
  private String applicantName;
  private String address;
  private String phoneNumber;
  private String career;
  private String korean;
  private String selfIntroduction;
  private Long employeeId;


  public Resume(String applicantName, String address, String phoneNumber, String career,
      String korean, String selfIntroduction,Long employeeId) {
    this.applicantName = applicantName;
    this.address = address;
    this.phoneNumber = phoneNumber;
    this.career = career;
    this.korean = korean;
    this.selfIntroduction = selfIntroduction;
    this.employeeId = employeeId;
  }

  public Resume() {
  }

  public Long getResumeId() {
    return resumeId;
  }

  public String getApplicantName() {
    return applicantName;
  }

  public String getAddress() {
    return address;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public String getCareer() {
    return career;
  }

  public String getKorean() {
    return korean;
  }

  public String getSelfIntroduction() {
    return selfIntroduction;
  }
}
