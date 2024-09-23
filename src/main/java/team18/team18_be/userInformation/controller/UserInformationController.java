package team18.team18_be.userInformation.controller;

import team18.team18_be.userInformation.dto.request.CompanyRequest;
import team18.team18_be.userInformation.dto.request.VisaRequest;
import team18.team18_be.userInformation.service.UserInformationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserInformationController {

  private final UserInformationService userInformationService;

  public UserInformationController(UserInformationService userInformationService) {
    this.userInformationService = userInformationService;
  }

//  @PostMapping("/sign")
//  public ResponseEntity<Void> RegisterSign(){
//  }

  @PostMapping("/company")
  public ResponseEntity<Void> RegisterCompany(@RequestBody CompanyRequest companyRequest) {
    userInformationService.RegisterCompany(companyRequest);
    return ResponseEntity.ok().build();
  }

  @PostMapping("/visa")
  public ResponseEntity<Void> RegisterVisa(@RequestBody VisaRequest visaRequest) {
    userInformationService.RegisterVisa(visaRequest);
    return ResponseEntity.ok().build();
  }
}
