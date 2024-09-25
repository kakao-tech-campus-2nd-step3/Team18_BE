package team18.team18_be.userInformation.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

//  @PostMapping("/sign")
//  public ResponseEntity<Void> RegisterSign(){
//  }

    @PostMapping("/company")
    public ResponseEntity<Void> createCompany(@RequestBody CompanyRequest companyRequest) {
        userInformationService.createCompany(companyRequest);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/visa")
    public ResponseEntity<Void> fillInVisa(@RequestBody VisaRequest visaRequest) {
        userInformationService.fillInVisa(visaRequest);
        return ResponseEntity.ok().build();
    }
}
