package team18.team18_be.recruitment.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class RecruitmentContent {

  @Id
  private Long resumeContentId;
  private String koreanDetailedDescription;
  private String vietnameseDetailedDescription;
  private Long recruitmentId;

  public RecruitmentContent(String koreanDetailedDescription,
      String vietnameseDetailedDescription, Long recruitmentId) {
    this.koreanDetailedDescription = koreanDetailedDescription;
    this.vietnameseDetailedDescription = vietnameseDetailedDescription;
    this.recruitmentId = recruitmentId;
  }

  public RecruitmentContent() {
  }

  public Long getResumeContentId() {
    return resumeContentId;
  }

  public String getKoreanDetailedDescription() {
    return koreanDetailedDescription;
  }

  public String getVietnameseDetailedDescription() {
    return vietnameseDetailedDescription;
  }

  public Long getRecruitmentId() {
    return recruitmentId;
  }
}
