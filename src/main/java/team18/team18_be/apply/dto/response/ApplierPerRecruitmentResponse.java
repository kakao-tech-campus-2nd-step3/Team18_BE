package team18.team18_be.apply.dto.response;

public record ApplierPerRecruitmentResponse(Long userId, String name, Long resumeId, Long applyId,
                                            String applicantNation, String korean) {

}
