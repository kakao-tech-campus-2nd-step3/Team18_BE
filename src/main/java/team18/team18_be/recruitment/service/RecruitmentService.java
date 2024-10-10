package team18.team18_be.recruitment.service;

import org.springframework.stereotype.Service;
import team18.team18_be.recruitment.dto.request.RecruitmentRequest;
import team18.team18_be.recruitment.entity.Recruitment;
import team18.team18_be.recruitment.entity.RecruitmentContent;
import team18.team18_be.recruitment.repository.RecruitmentContentRepository;
import team18.team18_be.recruitment.repository.RecruitmentRepository;

@Service
public class RecruitmentService {
  private RecruitmentRepository recruitmentRepository;
  private RecruitmentContentRepository recruitmentContentRepository;

  public RecruitmentService(RecruitmentRepository recruitmentRepository,
      RecruitmentContentRepository recruitmentContentRepository) {
    this.recruitmentRepository = recruitmentRepository;
    this.recruitmentContentRepository = recruitmentContentRepository;
  }

  public void saveRecruitment(RecruitmentRequest recruitmentRequest){

  }

  private Recruitment mapRecruitmentRequestToRecruitment(String koreanTitle,String vietnameseTitle,RecruitmentRequest recruitmentRequest){
    return new Recruitment(koreanTitle,vietnameseTitle,recruitmentRequest.companySize(),recruitmentRequest.area(),recruitmentRequest.salary(),recruitmentRequest.workDuration(),recruitmentRequest.workDays(), recruitmentRequest.workType(),recruitmentRequest.workHours(),recruitmentRequest.requestedCareer(),recruitmentRequest.majorBusiness(),recruitmentRequest.eligibilityCriteria(),recruitmentRequest.preferredConditions(),recruitmentRequest.employerName());
  }

  private RecruitmentContent mapRecruitmentRequestToRecruitmentContent(String koreanDetailedDescription,String vietnameseDetailedDescription,Long resumeId){
    return new RecruitmentContent(koreanDetailedDescription,vietnameseDetailedDescription,resumeId);
  }


}
