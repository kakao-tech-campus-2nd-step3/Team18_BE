package team18.team18_be.recruitment.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team18.team18_be.recruitment.dto.request.RecruitmentRequest;
import team18.team18_be.recruitment.dto.response.RecruitmentResponse;
import team18.team18_be.recruitment.dto.response.RecruitmentSummationResponse;

import java.util.List;

@Api(tags = {"구인글 관련 Controller"})
@RestController
@RequestMapping("/api/recruitments")
public class RecruitmentController {

    @ApiOperation(value = "구인글 저장 메서드")
    @PostMapping
    public ResponseEntity<Void> saveRecruitment(
            @RequestHeader("Authorization") String token,
            @RequestBody RecruitmentRequest recruitmentRequest
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "구인글 전체 조회 메서드")
    @GetMapping
    public ResponseEntity<List<RecruitmentSummationResponse>> getAllRecruitments() {
        return null;
    }

    @ApiOperation(value = "구인글id로 조회 메서드")
    @GetMapping("/{postId}")
    public ResponseEntity<RecruitmentResponse> getRecruitments(
            @PathVariable Long postId
    ) {
        return null;
    }

    @ApiOperation(value = "고용주 별 구인글 조회 메서드")
    @GetMapping
    public ResponseEntity<List<RecruitmentResponse>> getAllEmployerName(
            @RequestHeader("Authorization") String token
    ) {
        return null;
    }

}
