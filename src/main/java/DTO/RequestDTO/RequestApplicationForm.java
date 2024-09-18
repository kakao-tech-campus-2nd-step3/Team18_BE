package DTO.RequestDTO;

public class RequestApplicationForm {
  private String applyDate;
  private String applyMotivation;
  private String koreanLanguageSkill;
  private String personalHistory;
  private String etc;

  public RequestApplicationForm(String applyDate, String applyMotivation,
    String koreanLanguageSkill,
    String personalHistory, String etc) {
    this.applyDate = applyDate;
    this.applyMotivation = applyMotivation;
    this.koreanLanguageSkill = koreanLanguageSkill;
    this.personalHistory = personalHistory;
    this.etc = etc;
  }
}
