package team18.team18_be.contract.controller;

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

@RestController
@RequestMapping("/api/contract")
public class ContractController {

    private final ContractService contractService;

    public ContractController(ContractService contractService) {
        this.contractService = contractService;
    }

    @PostMapping
    public ResponseEntity<Void> makeContract(@Valid @RequestBody ContractRequest request,
        @LoginMember Member member) {
        contractService.makeContract(request, member);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<ContractResponse> getContract(@LoginMember Member member) {
        return ResponseEntity.ok(contractService.getContract(member));

    }

    @GetMapping("/{contractId}/download")
    public ResponseEntity<byte[]> downloadContract(@PathVariable("contractId") Long id,
        @LoginMember Member member) {
        return ResponseEntity.ok(contractService.downloadContract(id, member));
    }

    @GetMapping("/{contractId}/preview")
    public ResponseEntity<byte[]> previewContract(@PathVariable("contractId") Long id,
        @LoginMember Member member) {
        return ResponseEntity.ok(contractService.previewContract(id, member));
    }
}
