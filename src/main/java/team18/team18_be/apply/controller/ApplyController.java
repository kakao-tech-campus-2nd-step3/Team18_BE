package team18.team18_be.apply.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team18.team18_be.apply.dto.response.ApplyResponse;
import team18.team18_be.apply.service.ApplyService;
import team18.team18_be.auth.entity.User;
import team18.team18_be.config.resolver.LoginUser;
import team18.team18_be.userInformation.dto.request.ApplicationFormRequest;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/application")
public class ApplyController {

  private final ApplyService applyService;

  public ApplyController(ApplyService applyService) {
    this.applyService = applyService;
  }

  @PostMapping
  public ResponseEntity<Void> createApplicationForm(
          @RequestBody ApplicationFormRequest applicationFormRequest, @LoginUser User user) {
    Long applicationId = applyService.createApplicationForm(applicationFormRequest);
    URI location = URI.create("/api/application/" + applicationId);

    return ResponseEntity.created(location).build();
  }

  @GetMapping
  public ResponseEntity<List<ApplyResponse>> SearchApplicant(@LoginUser User user) {
    return ResponseEntity.ok(applyService.searchApplicacnt());
  }
}
