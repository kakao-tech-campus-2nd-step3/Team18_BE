package team18.team18_be.resume.service;

import org.springframework.stereotype.Service;
import team18.team18_be.resume.dto.request.ResumeRequest;
import team18.team18_be.resume.entity.Resume;
import team18.team18_be.resume.repository.ResumeRepository;

@Service
public class ResumeService {

  private ResumeRepository resumeRepository;

  public ResumeService(ResumeRepository resumeRepository) {
    this.resumeRepository = resumeRepository;
  }

  public void saveResume(ResumeRequest resumeRequest,Long employeeId){
    resumeRepository.save(MapResumeRequestToResume(resumeRequest,employeeId));
  }

  private Resume MapResumeRequestToResume(ResumeRequest resumeRequest, Long employeeId){
    return new Resume(resumeRequest.applicantName(),resumeRequest.address(),resumeRequest.phoneNumber(),resumeRequest.career(),resumeRequest.korean(),resumeRequest.selfIntroduction(),employeeId);
  }

}
