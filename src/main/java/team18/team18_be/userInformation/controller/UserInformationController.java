package team18.team18_be.userInformation.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import team18.team18_be.auth.entity.User;
import team18.team18_be.config.resolver.LoginUser;
import team18.team18_be.userInformation.dto.request.CompanyRequest;
import team18.team18_be.userInformation.dto.request.VisaRequest;
import team18.team18_be.userInformation.service.UserInformationService;

@RestController
@RequestMapping("/api")
public class UserInformationController {

  private final UserInformationService userInformationService;

  public UserInformationController(UserInformationService userInformationService) {
    this.userInformationService = userInformationService;
  }

  @PostMapping(value = "/sign", consumes = "multipart/form-data")
  public ResponseEntity<Void> fillInSign(@RequestParam("file") MultipartFile file,
      @LoginUser User user) {
    userInformationService.fillInSign(file);
    return ResponseEntity.noContent().build();
  }

  @PostMapping("/company")
  public ResponseEntity<Void> createCompany(@RequestBody CompanyRequest companyRequest,
      @LoginUser User user) {
    userInformationService.createCompany(companyRequest);
    return ResponseEntity.ok().build();
  }

  @PutMapping("/visa")
  public ResponseEntity<Void> fillInVisa(@RequestBody VisaRequest visaRequest,
      @LoginUser User user) {
    userInformationService.fillInVisa(visaRequest);
    return ResponseEntity.ok().build();
  }
}
