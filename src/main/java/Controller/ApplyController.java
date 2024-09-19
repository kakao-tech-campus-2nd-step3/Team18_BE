package Controller;

import DTO.RequestDTO.RequestApplicationForm;
import Service.ApplyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/application")
public class ApplyController {

  private final ApplyService applyService;

  public ApplyController(ApplyService applyService) {
    this.applyService = applyService;
  }

  @PostMapping
  public ResponseEntity<Void> RegisterApplicationForm(
    @RequestBody RequestApplicationForm requestApplicationForm) {
    applyService.RegisterApplicationForm(requestApplicationForm);

    return ResponseEntity.ok().build();
  }

//  @GetMapping
//  public ResponseEntity<Void>> SearchApplicant(){
//    return
//  }
}
