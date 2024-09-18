package Service;

import DTO.RequestDTO.RequestApplicationForm;
import Repository.ApplicationFormRepository;
import org.springframework.stereotype.Service;

@Service
public class ApplyService {
  private final ApplicationFormRepository applicationFormRepository;
  public ApplyService(ApplicationFormRepository applicationFormRepository){
    this.applicationFormRepository = applicationFormRepository;
  }

  public void RegisterApplicationForm(RequestApplicationForm requestApplicationForm) {
  }
}
