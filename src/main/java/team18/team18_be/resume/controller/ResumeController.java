package team18.team18_be.resume.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team18.team18_be.resume.dto.ResumeRequest;
import team18.team18_be.resume.dto.ResumeResponse;

@RestController
@RequestMapping("/api/resumes")
public class ResumeController {
    @PostMapping
    public ResponseEntity<Void> saveResume(
            @RequestHeader("Authorization") String token,
            @RequestBody ResumeRequest resumeRequest
    ){
        return null;
    }

    @GetMapping
    public ResponseEntity<ResumeResponse> getResume(
            @RequestHeader("Authorization") String token
    ){
        return null;
    }

    @GetMapping("/resumeId")
    public ResponseEntity<ResumeResponse> getResumeById(
            @RequestHeader("Authorization") String token
    ){
        return null;
    }

}
