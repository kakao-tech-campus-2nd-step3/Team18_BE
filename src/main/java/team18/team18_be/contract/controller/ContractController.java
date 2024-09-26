package team18.team18_be.contract.controller;

import io.swagger.annotations.ApiOperation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team18.team18_be.contract.dto.request.ContractRequest;
import team18.team18_be.contract.dto.response.ContractResponse;
import team18.team18_be.contract.service.ContractService;

@RestController
@RequestMapping("/api/contract")
public class ContractController {

    private final ContractService contractService;

    @ApiOperation(value = "근로계약서 등록 메서드 - 고용주 등록")
    @PostMapping
    public ResponseEntity<Void> makeContract(@Valid @RequestBody ContractRequest request, @LoginUser User user) {
        contractService.makeContract(request, user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "근로계약서 등록 메서드 - 근로자 서명 등록")
    @PostMapping("/employe")
    public ResponseEntity<Void> makeContract(@LoginUser User user) {
        contractService.makeContractEmployee(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "근로계약서 user id 별 조회 메서드")
    @GetMapping
    public ResponseEntity<ContractResponse> getContract(@LoginUser User user) {
        return ResponseEntity.ok(contractService.getContract(user));


        @GetMapping
        public ResponseEntity<ContractResponse> getContract (@LoginMember Member member){
            return ResponseEntity.ok(contractService.getContract(member));

            @ApiOperation(value = "근로계약서 id별 pdf url 반환 메서드")
            @GetMapping("/{contractId}/download")
            public ResponseEntity<String> downloadContract (@PathVariable("contractId") Long id, @LoginUser User user){
                return ResponseEntity.ok(contractService.downloadContract(id, user));
            }

            @ApiOperation(value = "근로계약서 id별 image url 반환 메서드")
            @GetMapping("/{contractId}/preview")
            public ResponseEntity<String> previewContract (@PathVariable("contractId") Long id, @LoginUser User user){
                return ResponseEntity.ok(contractService.previewContract(id, user));
            }

        }
