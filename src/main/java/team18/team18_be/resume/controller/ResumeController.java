package team18.team18_be.resume.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team18.team18_be.auth.entity.User;
import team18.team18_be.config.resolver.LoginUser;
import team18.team18_be.resume.dto.request.ResumeRequest;
import team18.team18_be.resume.dto.response.ResumeResponse;
import team18.team18_be.resume.service.ResumeService;

@Api(tags = {"이력서 관련 Controller"})
@RestController
@RequestMapping("/api/resumes")
public class ResumeController {

  private ResumeService resumeService;

  public ResumeController(ResumeService resumeService) {
    this.resumeService = resumeService;
  }

  @ApiOperation(value = "이력서 저장 메서드")
  @PostMapping
  public ResponseEntity<Void> saveResume(
      @RequestBody ResumeRequest resumeRequest,
      @LoginUser User user
  ) {
    resumeService.saveResume(resumeRequest, user.getId());
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @ApiOperation(value = "구직자 별 이력서 조회 메서드")
  @GetMapping
  public ResponseEntity<ResumeResponse> getResume(
      @LoginUser User user
  ) {
    return ResponseEntity.ok().body(resumeService.findResumeByEmployeeId(user.getId()));
  }

  @ApiOperation(value = "이력서 id로 이력서 조회 메서드")
  @GetMapping("/{resumeId}")
  public ResponseEntity<ResumeResponse> getResumeById(
      @PathVariable Long resumeId,
      @LoginUser User user
  ) {
    return ResponseEntity.ok().body(resumeService.findResumeById(resumeId, user.getId()));
  }

}
