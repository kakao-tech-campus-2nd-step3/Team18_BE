package team18.team18_be.userInformation.dto.request;

public class ApplicationFormRequest {

    private final String applyDate;
    private final String applyMotivation;
    private final String koreanLanguageSkill;
    private final String personalHistory;
    private final String etc;

    public ApplicationFormRequest(String applyDate, String applyMotivation,
        String koreanLanguageSkill,
        String personalHistory, String etc) {
        this.applyDate = applyDate;
        this.applyMotivation = applyMotivation;
        this.koreanLanguageSkill = koreanLanguageSkill;
        this.personalHistory = personalHistory;
        this.etc = etc;
    }
}
