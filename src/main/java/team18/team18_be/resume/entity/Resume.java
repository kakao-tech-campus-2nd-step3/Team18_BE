package team18.team18_be.resume.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import team18.team18_be.auth.entity.User;

@Entity
public class Resume {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long resumeId;
  private String applicantName;
  private String address;
  private String phoneNumber;
  private String career;
  private String korean;
  @Lob
  private String selfIntroduction;

  @ManyToOne
  @JoinColumn(name = "userId")
  private User user;

  public Resume(String applicantName, String address, String phoneNumber, String career,
      String korean, String selfIntroduction, User user) {
    this.applicantName = applicantName;
    this.address = address;
    this.phoneNumber = phoneNumber;
    this.career = career;
    this.korean = korean;
    this.selfIntroduction = selfIntroduction;
    this.user = user;
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
