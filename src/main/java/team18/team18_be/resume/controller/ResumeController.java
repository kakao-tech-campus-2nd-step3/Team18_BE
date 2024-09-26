package team18.team18_be.resume.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team18.team18_be.resume.dto.request.ResumeRequest;
import team18.team18_be.resume.dto.response.ResumeResponse;

@Api(tags = {"이력서 관련 Controller"})
@RestController
@RequestMapping("/api/resumes")
public class ResumeController {
    @ApiOperation(value = "이력서 저장 메서드")
    @PostMapping
    public ResponseEntity<Void> saveResume(
            @RequestHeader("Authorization") String token,
            @RequestBody ResumeRequest resumeRequest
    ){
        return null;
    }

    @ApiOperation(value = "구직자 별 이력서 조회 메서드")
    @GetMapping
    public ResponseEntity<ResumeResponse> getResume(
            @RequestHeader("Authorization") String token
    ){
        return null;
    }

    @ApiOperation(value = "이력서 id로 이력서 조회 메서드")
    @GetMapping("/resumeId")
    public ResponseEntity<ResumeResponse> getResumeById(
            @RequestHeader("Authorization") String token
    ){
        return null;
    }

}
