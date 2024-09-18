package team18.team18_be.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team18.team18_be.dto.RecruitmentsRequest;
import team18.team18_be.dto.RecruitmentsResponse;
import team18.team18_be.dto.RecruitmentsSummationResponse;

import java.util.List;

@RestController
@RequestMapping("/api/recruitments")
public class RecruitmentsController {
    @PostMapping
    public ResponseEntity<Void> saveRecruitment(
            @RequestHeader("Authorization") String token,
            @RequestBody RecruitmentsRequest recruitmentsRequest
            ){
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<RecruitmentsSummationResponse>> getAllRecruitments(){
        return null;
    }

    @GetMapping("/{postId}")
    public ResponseEntity<RecruitmentsResponse> getRecruitments(
            @PathVariable Long postId
    ){
        return null;
    }

    @GetMapping
    public ResponseEntity<List<RecruitmentsResponse>> getAllEmployerName(
            @RequestHeader("Authorization") String token
    ){
        return null;
    }

}
