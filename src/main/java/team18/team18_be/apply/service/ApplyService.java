package team18.team18_be.apply.service;

import java.util.List;
import team18.team18_be.apply.dto.response.ApplyResponse;
import team18.team18_be.userInformation.dto.request.RequestApplicationForm;
import team18.team18_be.apply.repository.ApplyRepository;
import org.springframework.stereotype.Service;

@Service
public class ApplyService {

  private final ApplyRepository applyRepository;

  public ApplyService(ApplyRepository applyRepository) {
    this.applyRepository = applyRepository;
  }

  public void registerApplicationForm(RequestApplicationForm requestApplicationForm) {
  }

  public List<ApplyResponse> searchApplicacnt() {
    return null;
  }
}
