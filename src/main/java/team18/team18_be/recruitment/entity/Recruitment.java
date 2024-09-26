package team18.team18_be.recruitment.entity;

public class Recruitment {
    private Long recruitmentId;
    private String title;
    private String companySize;
    private String area;
    private String salary;
    private String workDuration;
    private String workType;
    private String workHours;
    private String requestedCareer;
    private String majorBusiness;
    private String eligibilityCriteria;
    private String preferredConditions;
    private String employerName;
    private String detailedDescription;

    public Recruitment(Long recruitmentId, String title, String companySize, String area, String salary, String workDuration, String workType, String workHours, String requestedCareer, String majorBusiness, String eligibilityCriteria, String preferredConditions, String employerName, String detailedDescription) {
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
