package Controller;

import DTO.RequestDTO.RequestCompany;
import DTO.RequestDTO.RequestVisa;
import Service.UserInformationService;
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
  public ResponseEntity<Void> RegisterCompany(@RequestBody RequestCompany requestCompany) {
    userInformationService.RegisterCompany(requestCompany);
    return ResponseEntity.ok().build();
  }

  @PostMapping("/visa")
  public ResponseEntity<Void> RegisterVisa(@RequestBody RequestVisa requestVisa) {
    userInformationService.RegisterVisa(requestVisa);
    return ResponseEntity.ok().build();
  }
}
