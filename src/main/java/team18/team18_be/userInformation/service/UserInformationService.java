package team18.team18_be.userInformation.service;

import team18.team18_be.userInformation.dto.request.CompanyRequest;
import team18.team18_be.userInformation.dto.request.VisaRequest;
import team18.team18_be.userInformation.repository.UserInformationRepository;
import org.springframework.stereotype.Service;

@Service
public class UserInformationService {

  private final UserInformationRepository userInformationRepository;

  public UserInformationService(UserInformationRepository userInformationRepository) {
    this.userInformationRepository = userInformationRepository;
  }

  public void createCompany(CompanyRequest companyRequest) {
  }

  public void fillInVisa(VisaRequest visaRequest) {
  }
}
