package team18.team18_be.recruitment.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team18.team18_be.recruitment.dto.request.RecruitmentRequest;
import team18.team18_be.recruitment.dto.response.RecruitmentResponse;
import team18.team18_be.recruitment.dto.response.RecruitmentSummationResponse;

import java.util.List;

@RestController
@RequestMapping("/api/recruitments")
public class RecruitmentController {
    @PostMapping
    public ResponseEntity<Void> saveRecruitment(
            @RequestHeader("Authorization") String token,
            @RequestBody RecruitmentRequest recruitmentRequest
            ){
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<RecruitmentSummationResponse>> getAllRecruitments(){
        return null;
    }

    @GetMapping("/{postId}")
    public ResponseEntity<RecruitmentResponse> getRecruitments(
            @PathVariable Long postId
    ){
        return null;
    }

    @GetMapping
    public ResponseEntity<List<RecruitmentResponse>> getAllEmployerName(
            @RequestHeader("Authorization") String token
    ){
        return null;
    }

}
