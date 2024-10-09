package team18.team18_be.recruitment.entity;

public class Recruitment {

  private final Long recruitmentId;
  private final String title;
  private final String companySize;
  private final String area;
  private final String salary;
  private final String workDuration;
  private final String workType;
  private final String workHours;
  private final String requestedCareer;
  private final String majorBusiness;
  private final String eligibilityCriteria;
  private final String preferredConditions;
  private final String employerName;
  private final String detailedDescription;

  public Recruitment(Long recruitmentId, String title, String companySize, String area,
      String salary, String workDuration, String workType, String workHours, String requestedCareer,
      String majorBusiness, String eligibilityCriteria, String preferredConditions,
      String employerName, String detailedDescription) {
    this.recruitmentId = recruitmentId;
    this.title = title;
    this.companySize = companySize;
    this.area = area;
    this.salary = salary;
    this.workDuration = workDuration;
    this.workType = workType;
    this.workHours = workHours;
    this.requestedCareer = requestedCareer;
    this.majorBusiness = majorBusiness;
    this.eligibilityCriteria = eligibilityCriteria;
    this.preferredConditions = preferredConditions;
    this.employerName = employerName;
    this.detailedDescription = detailedDescription;
  }
}
