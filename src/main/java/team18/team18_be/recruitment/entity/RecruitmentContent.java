package team18.team18_be.recruitment.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class RecruitmentContent {
  @Id
  private Long resumeContentId;
  private String koreanDetailedDescription;
  private String vietnameseDetailedDescription;
  private Long resumeId;

  public RecruitmentContent(String koreanDetailedDescription,
      String vietnameseDetailedDescription, Long resumeId) {
    this.koreanDetailedDescription = koreanDetailedDescription;
    this.vietnameseDetailedDescription = vietnameseDetailedDescription;
    this.resumeId = resumeId;
  }

  public RecruitmentContent() {
  }
}
