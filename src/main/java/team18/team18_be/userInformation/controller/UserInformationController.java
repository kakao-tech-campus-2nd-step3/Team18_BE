package team18.team18_be.userInformation.controller;

import org.springframework.web.bind.annotation.PutMapping;
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

  public UserInformationController(UserInformationService userInformationService,@LoginUser User user) {
    this.userInformationService = userInformationService;
  }

//  @PostMapping("/sign")
//  public ResponseEntity<Void> RegisterSign(@LoginUser User user){
//  }

  @PostMapping("/company")
  public ResponseEntity<Void> createCompany(@RequestBody CompanyRequest companyRequest, @LoginUser User user) {
    userInformationService.createCompany(companyRequest);
    return ResponseEntity.ok().build();
  }

  @PutMapping("/visa")
  public ResponseEntity<Void> fillInVisa(@RequestBody VisaRequest visaRequest,@LoginUser User user) {
    userInformationService.fillInVisa(visaRequest);
    return ResponseEntity.ok().build();
  }
}
