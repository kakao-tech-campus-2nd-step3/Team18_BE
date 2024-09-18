package Service;

import DTO.RequestDTO.RequestCompany;
import DTO.RequestDTO.RequestVisa;
import Repository.UserInformationRepository;
import org.springframework.stereotype.Service;

@Service
public class UserInformationService {
  private final UserInformationRepository userInformationRepository;

  public UserInformationService(UserInformationRepository userInformationRepository){
    this.userInformationRepository=userInformationRepository;
  }

  public void RegisterCompany(RequestCompany requestCompany) {
  }

  public void RegisterVisa(RequestVisa requestVisa) {
  }
}
