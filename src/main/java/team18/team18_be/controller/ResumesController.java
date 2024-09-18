package team18.team18_be.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team18.team18_be.dto.ResumesRequest;
import team18.team18_be.dto.ResumesResponse;

@RestController
@RequestMapping("/api/resumes")
public class ResumesController {
    @PostMapping
    public ResponseEntity<Void> saveResume(
            @RequestHeader("Authorization") String token,
            @RequestBody ResumesRequest resumesRequest
    ){
        return null;
    }

    @GetMapping
    public ResponseEntity<ResumesResponse> getResume(
            @RequestHeader("Authorization") String token
    ){
        return null;
    }
}
