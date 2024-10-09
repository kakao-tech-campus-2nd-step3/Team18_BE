package team18.team18_be.recruitment.entity;

public class ResumeContent {

  private final Long resumeContentId;
  private final String koreanDetailedDescription;
  private final String vietnameseDetailedDescription;
  private final Long resumeId;

  public ResumeContent(Long resumeContentId, String koreanDetailedDescription,
      String vietnameseDetailedDescription, Long resumeId) {
    this.resumeContentId = resumeContentId;
    this.koreanDetailedDescription = koreanDetailedDescription;
    this.vietnameseDetailedDescription = vietnameseDetailedDescription;
    this.resumeId = resumeId;
  }
}
