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

  public Recruitment( String koreanTitle, String vietnameseTitle, String companySize, String area,
      String salary, String workDuration,String workDays, String workType, String workHours, String requestedCareer,
      String majorBusiness, String eligibilityCriteria, String preferredConditions,
      String employerName) {
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
  }

  public Long getRecruitmentId() {
    return recruitmentId;
  }

  public Recruitment() {
    
  }
}
