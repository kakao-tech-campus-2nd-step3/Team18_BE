package team18.team18_be.recruitment.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import team18.team18_be.config.infrastructure.OpenAiService;
import team18.team18_be.recruitment.dto.request.RecruitmentRequest;
import team18.team18_be.recruitment.dto.response.RecruitmentResponse;
import team18.team18_be.recruitment.dto.response.RecruitmentSummationResponse;
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
      RecruitmentContentRepository recruitmentContentRepository, OpenAiService openAiService) {
    this.recruitmentRepository = recruitmentRepository;
    this.recruitmentContentRepository = recruitmentContentRepository;
    this.openAiService = openAiService;
  }

  public void saveRecruitment(RecruitmentRequest recruitmentRequest)
      throws JsonProcessingException {
    String koreanTitle = recruitmentRequest.title();
    String vietnameseTitle = openAiService.translateKoreanToVietnamese(koreanTitle);
    Recruitment recruitment = recruitmentRepository.save(
        mapRecruitmentRequestToRecruitment(koreanTitle, vietnameseTitle, recruitmentRequest));

    String koreanDetailedDescription = openAiService.summation(recruitmentRequest);
    String vietnameseDetailedDescription = openAiService.translateKoreanToVietnamese(
        koreanDetailedDescription);
    recruitmentContentRepository.save(
        new RecruitmentContent(koreanDetailedDescription, vietnameseDetailedDescription,
            recruitment.getRecruitmentId()));
  }

  public List<RecruitmentSummationResponse> getAllRecruitment() {
    List<Recruitment> recruitments = recruitmentRepository.findAll();
    return recruitments.stream()
        .map(recruitment -> new RecruitmentSummationResponse(
            recruitment.getRecruitmentId(),
            "image ",
            recruitment.getKoreanTitle(),
            recruitment.getVietnameseTitle(),
            recruitment.getCompanyName(),
            recruitment.getSalary(),
            recruitment.getArea()
        ))
        .collect(Collectors.toList());
  }

  public RecruitmentResponse getRecruitmentResponseByRecruitmentId(Long userId) {
    Recruitment recruitment = recruitmentRepository.findById(userId)
        .orElseThrow(() -> new NoSuchElementException("해당하는 이력서가 존재하지 않습니다."));

    RecruitmentContent recruitmentContent = recruitmentContentRepository.findByRecruitmentId(
        userId);
    return mapRecruitmentAndRecruitmentContentToRecruitmentResponse(recruitment,
        recruitmentContent);
  }

  public RecruitmentResponse getRecruitmentResponseByCompanyId(Long recruitmentId) {
    return null;
  }

  private Recruitment mapRecruitmentRequestToRecruitment(String koreanTitle, String vietnameseTitle,
      RecruitmentRequest recruitmentRequest) {
    return new Recruitment(koreanTitle, vietnameseTitle, recruitmentRequest.companySize(),
        recruitmentRequest.area(), recruitmentRequest.salary(), recruitmentRequest.workDuration(),
        recruitmentRequest.workDays(), recruitmentRequest.workType(),
        recruitmentRequest.workHours(), recruitmentRequest.requestedCareer(),
        recruitmentRequest.majorBusiness(), recruitmentRequest.eligibilityCriteria(),
        recruitmentRequest.preferredConditions(), recruitmentRequest.employerName(),
        recruitmentRequest.companyName(), recruitmentRequest.companyId());
  }

  private RecruitmentResponse mapRecruitmentAndRecruitmentContentToRecruitmentResponse(
      Recruitment recruitment, RecruitmentContent recruitmentContent) {
    return new RecruitmentResponse(recruitment.getKoreanTitle(), recruitment.getVietnameseTitle(),
        recruitment.getCompanySize(), recruitment.getArea(), recruitment.getSalary(),
        recruitment.getWorkDuration(), recruitment.getWorkDays(), recruitment.getWorkType(),
        recruitment.getWorkHours(), recruitment.getRequestedCareer(),
        recruitment.getMajorBusiness(), recruitment.getEligibilityCriteria(),
        recruitment.getPreferredConditions(), recruitment.getEmployerName(),
        recruitmentContent.getKoreanDetailedDescription(),
        recruitmentContent.getVietnameseDetailedDescription());
  }

}
