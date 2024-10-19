package team18.team18_be.resume.service;

import java.util.NoSuchElementException;
import java.util.Objects;
import org.springframework.stereotype.Service;
import team18.team18_be.resume.dto.request.ResumeRequest;
import team18.team18_be.resume.dto.response.ResumeResponse;
import team18.team18_be.resume.entity.Resume;
import team18.team18_be.resume.repository.ResumeRepository;

@Service
public class ResumeService {

  private final ResumeRepository resumeRepository;

  public ResumeService(ResumeRepository resumeRepository) {
    this.resumeRepository = resumeRepository;
  }

  public void saveResume(ResumeRequest resumeRequest, Long employeeId) {
    resumeRepository.save(mapResumeRequestToResume(resumeRequest, employeeId));
  }

  public ResumeResponse findResumeByEmployeeId(Long employeeId) {
    return mapResumeToResumeResponse(resumeRepository.findByEmployeeId(employeeId));
  }

  public ResumeResponse findResumeById(Long resumeId, Long userId) {
    Resume resume = resumeRepository.findById(resumeId)
        .orElseThrow(() -> new NoSuchElementException("해당하는 이력서가 존재하지 않습니다."));

    if (!Objects.equals(resume.getResumeId(), userId)) {
      //에러(권한없음)
    }

    return mapResumeToResumeResponse(resume);
  }

  private Resume mapResumeRequestToResume(ResumeRequest resumeRequest, Long employeeId) {
    return new Resume(resumeRequest.applicantName(), resumeRequest.address(),
        resumeRequest.phoneNumber(), resumeRequest.career(), resumeRequest.korean(),
        resumeRequest.selfIntroduction(), employeeId);
  }

  private ResumeResponse mapResumeToResumeResponse(Resume resume) {
    return new ResumeResponse(resume.getResumeId(), resume.getApplicantName(), resume.getAddress(),
        resume.getPhoneNumber(), resume.getCareer(), resume.getKorean(),
        resume.getSelfIntroduction());
  }

}
