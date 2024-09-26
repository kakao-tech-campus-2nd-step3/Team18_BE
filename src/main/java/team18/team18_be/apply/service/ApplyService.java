package team18.team18_be.apply.service;

import org.springframework.stereotype.Service;
import team18.team18_be.apply.dto.response.ApplyResponse;
import team18.team18_be.apply.repository.ApplyRepository;
import team18.team18_be.userInformation.dto.request.ApplicationFormRequest;

import java.util.List;

@Service
public class ApplyService {

  private final ApplyRepository applyRepository;

  public ApplyService(ApplyRepository applyRepository) {
    this.applyRepository = applyRepository;
  }

  public Long createApplicationForm(ApplicationFormRequest applicationFormRequest) {
    return null;
  }

  public List<ApplyResponse> searchApplicacnt() {
    return null;
  }
}
