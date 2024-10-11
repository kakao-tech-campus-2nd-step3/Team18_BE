package team18.team18_be.apply.service;

import java.util.List;
import org.springframework.stereotype.Service;
import team18.team18_be.apply.dto.response.ApplyResponse;
import team18.team18_be.apply.repository.ApplicationFormRepository;
import team18.team18_be.userInformation.dto.request.ApplicationFormRequest;

@Service
public class ApplyService {

  private final ApplicationFormRepository applicationFormRepository;

  public ApplyService(ApplicationFormRepository applicationFormRepository) {
    this.applicationFormRepository = applicationFormRepository;
  }

  public Long createApplicationForm(ApplicationFormRequest applicationFormRequest) {
    return null;
  }

  public List<ApplyResponse> searchApplicacnt() {
    return null;
  }
}
