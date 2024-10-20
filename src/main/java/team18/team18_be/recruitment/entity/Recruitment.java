package team18.team18_be.recruitment.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Recruitment {

  @Id
  private Long recruitmentId;
  private String koreanTitle;
  private String vietnameseTitle;
  private String companySize;
  private String area;
  private String salary;
  private String workDuration;
  private String workDays;
  private String workType;
  private String workHours;
  private String requestedCareer;
  private String majorBusiness;
  private String eligibilityCriteria;
  private String preferredConditions;
  private String employerName;
  private String companyName;
  private Long companyId;

  public Recruitment(String koreanTitle, String vietnameseTitle, String companySize, String area,
      String salary, String workDuration, String workDays, String workType, String workHours,
      String requestedCareer,
      String majorBusiness, String eligibilityCriteria, String preferredConditions,
      String employerName, String companyName, Long companyId) {
    this.koreanTitle = koreanTitle;
    this.vietnameseTitle = vietnameseTitle;
    this.companySize = companySize;
    this.area = area;
    this.salary = salary;
    this.workDuration = workDuration;
    this.workDays = workDays;
    this.workType = workType;
    this.workHours = workHours;
    this.requestedCareer = requestedCareer;
    this.majorBusiness = majorBusiness;
    this.eligibilityCriteria = eligibilityCriteria;
    this.preferredConditions = preferredConditions;
    this.employerName = employerName;
    this.companyName = companyName;
    this.companyId = companyId;
  }

  public Recruitment() {

  }

  public Long getRecruitmentId() {
    return recruitmentId;
  }

  public String getKoreanTitle() {
    return koreanTitle;
  }

  public String getVietnameseTitle() {
    return vietnameseTitle;
  }

  public String getCompanySize() {
    return companySize;
  }

  public String getArea() {
    return area;
  }

  public String getSalary() {
    return salary;
  }

  public String getWorkDuration() {
    return workDuration;
  }

  public String getWorkDays() {
    return workDays;
  }

  public String getWorkType() {
    return workType;
  }

  public String getWorkHours() {
    return workHours;
  }

  public String getRequestedCareer() {
    return requestedCareer;
  }

  public String getMajorBusiness() {
    return majorBusiness;
  }

  public String getEligibilityCriteria() {
    return eligibilityCriteria;
  }

  public String getPreferredConditions() {
    return preferredConditions;
  }

  public String getEmployerName() {
    return employerName;
  }

  public String getCompanyName() {
    return companyName;
  }

}
