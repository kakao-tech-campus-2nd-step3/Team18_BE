package team18.team18_be.recruitment.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;
import team18.team18_be.config.infrastructure.OpenAiService;
import team18.team18_be.recruitment.dto.request.RecruitmentRequest;
import team18.team18_be.recruitment.entity.Recruitment;
import team18.team18_be.recruitment.entity.RecruitmentContent;
import team18.team18_be.recruitment.repository.RecruitmentContentRepository;
import team18.team18_be.recruitment.repository.RecruitmentRepository;

@Service
public class RecruitmentService {
  private RecruitmentRepository recruitmentRepository;
  private RecruitmentContentRepository recruitmentContentRepository;

  private OpenAiService openAiService;

  public RecruitmentService(RecruitmentRepository recruitmentRepository,
      RecruitmentContentRepository recruitmentContentRepository,OpenAiService openAiService) {
    this.recruitmentRepository = recruitmentRepository;
    this.recruitmentContentRepository = recruitmentContentRepository;
    this.openAiService = openAiService;
  }

  public void saveRecruitment(RecruitmentRequest recruitmentRequest)
      throws JsonProcessingException {
    String koreanTitle = recruitmentRequest.title();
    String vietnameseTitle = openAiService.translateKoreanToVietnamese(koreanTitle);
    Recruitment recruitment = recruitmentRepository.save(mapRecruitmentRequestToRecruitment(koreanTitle,vietnameseTitle,recruitmentRequest));

    String koreanDetailedDescription = openAiService.summation(recruitmentRequest);
    String vietnameseDetailedDescription = openAiService.translateKoreanToVietnamese(koreanDetailedDescription);
    recruitmentContentRepository.save(new RecruitmentContent(koreanDetailedDescription,vietnameseDetailedDescription,recruitment.getRecruitmentId()));
  }

  private Recruitment mapRecruitmentRequestToRecruitment(String koreanTitle,String vietnameseTitle,RecruitmentRequest recruitmentRequest){
    return new Recruitment(koreanTitle,vietnameseTitle,recruitmentRequest.companySize(),recruitmentRequest.area(),recruitmentRequest.salary(),recruitmentRequest.workDuration(),recruitmentRequest.workDays(), recruitmentRequest.workType(),recruitmentRequest.workHours(),recruitmentRequest.requestedCareer(),recruitmentRequest.majorBusiness(),recruitmentRequest.eligibilityCriteria(),recruitmentRequest.preferredConditions(),recruitmentRequest.employerName(),recruitmentRequest.companyName());
  }

}
