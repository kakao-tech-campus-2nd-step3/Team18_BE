package team18.team18_be.apply.controller;

import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team18.team18_be.apply.dto.response.ApplyResponse;
import team18.team18_be.apply.service.ApplyService;
import team18.team18_be.userInformation.dto.request.ApplicationFormRequest;

@RestController
@RequestMapping("/api/application")
public class ApplyController {

  private final ApplyService applyService;

  public ApplyController(ApplyService applyService) {
    this.applyService = applyService;
  }

  @PostMapping
  public ResponseEntity<Void> registerApplicationForm(
    @RequestBody ApplicationFormRequest applicationFormRequest) {
    applyService.registerApplicationForm(applicationFormRequest);

    //URI경로를 로그인 정보에서 가져와 사용?
    return ResponseEntity.created(URI.create("applicationForm")).build();
  }

  @GetMapping
  public ResponseEntity<List<ApplyResponse>> SearchApplicant() {
    return ResponseEntity.ok(applyService.searchApplicacnt());
  }
}
