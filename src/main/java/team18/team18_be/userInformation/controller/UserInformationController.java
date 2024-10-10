package team18.team18_be.userInformation.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import team18.team18_be.auth.entity.User;
import team18.team18_be.auth.service.AuthService;
import team18.team18_be.config.resolver.LoginUser;
import team18.team18_be.userInformation.dto.request.CompanyRequest;
import team18.team18_be.userInformation.dto.request.CompanyResponse;
import team18.team18_be.userInformation.dto.request.SignResponse;
import team18.team18_be.userInformation.dto.request.VisaRequest;
import team18.team18_be.userInformation.dto.request.VisaResponse;
import team18.team18_be.userInformation.service.UserInformationService;

@RestController
@RequestMapping("/api")
public class UserInformationController {

  private final UserInformationService userInformationService;

  public UserInformationController(UserInformationService userInformationService,
      AuthService authService) {
    this.userInformationService = userInformationService;
  }

  //  @PostMapping(value = "/sign", consumes = "multipart/form-data")
//  public ResponseEntity<Void> fillInSign(@RequestParam("file") MultipartFile file,
//      @LoginUser User user) {
//    userInformationService.fillInSign(file);
//    return ResponseEntity.noContent().build();
//  }
  @PostMapping("/sign")
  public ResponseEntity<Void> fillInSign(@RequestParam MultipartFile signImg) {
    userInformationService.fillInSign(signImg);
    return ResponseEntity.noContent().build();
  }

//  @GetMapping(value="/sign")
//  public ResponseEntity<SignResponse> findSign(@LoginUser User user){
//    return ResponseEntity.ok(userInformationService.findSign(user));
//  }
  @GetMapping(value="/sign")
  public ResponseEntity<SignResponse> findSign(){
    return ResponseEntity.ok(userInformationService.findSign());
  }

  //  @PostMapping("/company")
//  public ResponseEntity<Void> createCompany(@RequestPart CompanyRequest companyRequest,
//      @RequestPart MultipartFile multipartFile, @LoginUser User user) {
//    userInformationService.createCompany(companyRequest,multipartFile);
//    return ResponseEntity.ok().build();
//  }
  @PostMapping("/company")
  public ResponseEntity<Void> createCompany(@RequestPart CompanyRequest companyRequest,
      @RequestPart MultipartFile logo) {
    userInformationService.createCompany(companyRequest, logo);
    return ResponseEntity.ok().build();
  }

  @GetMapping("/company")
  public ResponseEntity<CompanyResponse> findCompany(){
    return ResponseEntity.ok(userInformationService.findCompany());
  }

//  @PutMapping("/visa")
//  public ResponseEntity<Void> fillInVisa(@RequestBody VisaRequest visaRequest,
//      @LoginUser User user) {
//    userInformationService.fillInVisa(visaRequest, user);
//    return ResponseEntity.ok().build();
//  }
  @PutMapping("/visa")
  public ResponseEntity<Void> fillInVisa(@RequestBody VisaRequest visaRequest) {
    userInformationService.fillInVisa(visaRequest);
    return ResponseEntity.ok().build();
  }

//  @GetMapping("/visa")
//  public ResponseEntity<VisaResponse> findVisa(@LoginUser User user) {
//    VisaResponse visaResponse = userInformationService.findVisa(user);
//    return ResponseEntity.ok(visaResponse);
//  }
  @GetMapping("/visa")
  public ResponseEntity<VisaResponse> findVisa() {
    VisaResponse visaResponse = userInformationService.findVisa();
    return ResponseEntity.ok(visaResponse);
  }
}
