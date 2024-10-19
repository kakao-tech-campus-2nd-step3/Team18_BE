package team18.team18_be.apply.controller;

import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team18.team18_be.apply.dto.response.ApplyResponse;
import team18.team18_be.apply.service.ApplyService;
import team18.team18_be.auth.entity.User;
import team18.team18_be.config.resolver.LoginUser;
import team18.team18_be.userInformation.dto.request.ApplicationFormRequest;

@RestController
@RequestMapping("/api/application")
public class ApplyController {

  private final ApplyService applyService;

  public ApplyController(ApplyService applyService) {
    this.applyService = applyService;
  }

  @PostMapping("/{recruitmentId}")
  public ResponseEntity<Void> createApplicationForm(
      @RequestBody ApplicationFormRequest applicationFormRequest, @PathVariable Long recruitmentId,
      @LoginUser User user) {
    Long applicationId = applyService.createApplicationForm(applicationFormRequest, recruitmentId,
        user);
    URI location = URI.create("/api/application/" + applicationId);

    return ResponseEntity.created(location).build();
  }

  @GetMapping("/{recruitmentId}")
  public ResponseEntity<List<ApplyResponse>> SearchApplicant(@PathVariable Long recruitmentId,
      @LoginUser User user) {
    return ResponseEntity.ok(applyService.searchApplicant(recruitmentId, user));
  }

  //title,area,salary, 회사로고이미지
//  @GetMapping("/all")
//  public ResponseEntity<>
}
