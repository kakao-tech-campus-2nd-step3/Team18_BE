package team18.team18_be.recruitment.entity;

public class ResumeContent {
    private Long resumeContentId;
    private String koreanDetailedDescription;
    private String vietnameseDetailedDescription;
    private Long resumeId;

    public ResumeContent(Long resumeContentId, String koreanDetailedDescription, String vietnameseDetailedDescription, Long resumeId) {
        this.resumeContentId = resumeContentId;
        this.koreanDetailedDescription = koreanDetailedDescription;
        this.vietnameseDetailedDescription = vietnameseDetailedDescription;
        this.resumeId = resumeId;
    }
}
