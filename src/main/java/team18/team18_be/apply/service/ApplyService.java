package team18.team18_be.apply.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.stereotype.Service;
import team18.team18_be.apply.dto.response.ApplyResponse;
import team18.team18_be.apply.entity.ApplicationForm;
import team18.team18_be.apply.entity.Apply;
import team18.team18_be.apply.repository.ApplicationFormRepository;
import team18.team18_be.apply.repository.ApplyRepository;
import team18.team18_be.auth.entity.User;
import team18.team18_be.recruitment.entity.Recruitment;
import team18.team18_be.recruitment.repository.RecruitmentRepository;
import team18.team18_be.resume.entity.Resume;
import team18.team18_be.resume.repository.ResumeRepository;
import team18.team18_be.userInformation.dto.request.ApplicationFormRequest;

@Service
public class ApplyService {

  private final ApplicationFormRepository applicationFormRepository;
  private final ApplyRepository applyRepository;
  private final RecruitmentRepository recruitmentRepository;
  private final ResumeRepository resumeRepository;

  public ApplyService(ApplicationFormRepository applicationFormRepository,
      ApplyRepository applyRepository, RecruitmentRepository recruitmentRepository,
      ResumeRepository resumeRepository) {
    this.applicationFormRepository = applicationFormRepository;
    this.applyRepository = applyRepository;
    this.recruitmentRepository = recruitmentRepository;
    this.resumeRepository = resumeRepository;
  }

  public Long createApplicationForm(ApplicationFormRequest applicationFormRequest,
      Long recruitmentId, User user) {
    String status = "지원 중";
    Recruitment recruitment = recruitmentRepository.findById(recruitmentId)
        .orElseThrow(() -> new NoSuchElementException("해당되는 구인글이 없습니다."));
    Apply apply = new Apply(status, user, recruitment);
    Apply savedApply = applyRepository.save(apply);

    return savedApply.getId();
  }

  public List<ApplyResponse> searchApplicant(Long recruitmentId, User user) {
    Recruitment recruitment = recruitmentRepository.findById(recruitmentId)
        .orElseThrow(() -> new NoSuchElementException("해당되는 구인글이 없습니다."));
    List<Apply> applyList = applyRepository.findByRecruitment(recruitment);
    List<ApplyResponse> applyResponseList = new ArrayList<>();
    for (Apply apply : applyList) {
      ApplicationForm applicationForm = applicationFormRepository.findByApply(apply); //지원서 가져오기
      Long applicantId = apply.getUser().getId(); //구인글에 지원한 지원자
      Resume resume = resumeRepository.findByEmployeeId(applicantId); //그 지원자의 이력서 가져오기
      ApplyResponse applyResponse = new ApplyResponse(applicantId,
          applicationForm.getName(), applicationForm.getMotivation(), resume.getResumeId());
      applyResponseList.add(applyResponse);
    }
    return applyResponseList;
  }
}
