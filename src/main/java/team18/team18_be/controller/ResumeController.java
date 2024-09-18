package team18.team18_be.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team18.team18_be.dto.ResumeRequest;
import team18.team18_be.dto.ResumeResponse;

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
}
