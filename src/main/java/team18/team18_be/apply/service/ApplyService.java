package team18.team18_be.apply.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.stereotype.Service;
import team18.team18_be.apply.dto.response.ApplierPerRecruitmentResponse;
import team18.team18_be.apply.dto.response.RecruitmentsOfApplierResponse;
import team18.team18_be.apply.entity.Apply;
import team18.team18_be.apply.repository.ApplicationFormRepository;
import team18.team18_be.apply.repository.ApplyRepository;
import team18.team18_be.auth.entity.User;
import team18.team18_be.recruitment.entity.Recruitment;
import team18.team18_be.recruitment.repository.RecruitmentRepository;
import team18.team18_be.resume.entity.Resume;
import team18.team18_be.resume.repository.ResumeRepository;
import team18.team18_be.userInformation.dto.request.ApplicationFormRequest;
import team18.team18_be.userInformation.repository.CompanyRepository;

@Service
public class ApplyService {

  private final ApplicationFormRepository applicationFormRepository;
  private final ApplyRepository applyRepository;
  private final RecruitmentRepository recruitmentRepository;
  private final ResumeRepository resumeRepository;
  private final CompanyRepository companyRepository;


  public ApplyService(ApplicationFormRepository applicationFormRepository,
      ApplyRepository applyRepository, RecruitmentRepository recruitmentRepository,
      ResumeRepository resumeRepository, CompanyRepository companyRepository) {
    this.applicationFormRepository = applicationFormRepository;
    this.applyRepository = applyRepository;
    this.recruitmentRepository = recruitmentRepository;
    this.resumeRepository = resumeRepository;
    this.companyRepository = companyRepository;
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


  public List<ApplierPerRecruitmentResponse> searchApplicant(Long recruitmentId, User user) {
    Recruitment recruitment = recruitmentRepository.findById(recruitmentId)
        .orElseThrow(() -> new NoSuchElementException("해당되는 구인글이 없습니다."));
    List<Apply> applyList = applyRepository.findByRecruitment(recruitment);
    List<ApplierPerRecruitmentResponse> applyResponseList = new ArrayList<>();
    for (Apply apply : applyList) {
      User applicantUser = apply.getUser(); //구인글에 지원한 지원자
      Resume resume = resumeRepository.findByEmployeeId(applicantUser.getId()); //그 지원자의 이력서 가져오기
      ApplierPerRecruitmentResponse applierPerRecruitmentResponse = new ApplierPerRecruitmentResponse(
          applicantUser.getId(), applicantUser.getName(), resume.getResumeId(), apply.getId(),
          "베트남", resume.getKorean());
      applyResponseList.add(applierPerRecruitmentResponse);
    }
    return applyResponseList;
  }

  public List<RecruitmentsOfApplierResponse> SearchMyAppliedRecruitments(User user) {
    List<RecruitmentsOfApplierResponse> recruitmentsOfApplierResponseList = new ArrayList<>();
    List<Apply> applys = applyRepository.findByUser(user); //지원자의 지원 가져오기
    for (Apply apply : applys) {
      Recruitment recruitment = recruitmentRepository.findById(apply.getRecruitment().getRecruitmentId())
          .orElseThrow(()->new NoSuchElementException("해당 공고가 없습니다."));

    }
  }
}
