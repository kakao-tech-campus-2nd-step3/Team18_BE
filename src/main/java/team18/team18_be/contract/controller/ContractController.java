package team18.team18_be.contract.controller;

import com.itextpdf.text.DocumentException;
import io.swagger.annotations.ApiOperation;
import jakarta.validation.Valid;
import java.io.IOException;
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
import team18.team18_be.contract.dto.request.ContractRequest;
import team18.team18_be.contract.dto.response.ContractResponse;
import team18.team18_be.contract.service.ContractFileService;
import team18.team18_be.contract.service.ContractPdfService;
import team18.team18_be.contract.service.ContractService;

@RestController
@RequestMapping("/api/contract")
public class ContractController {

  private final ContractService contractService;
  private final ContractPdfService pdfService;
  private final ContractFileService fileService;

  public ContractController(ContractService contractService, ContractPdfService pdfService,
      ContractFileService fileService) {
    this.contractService = contractService;
    this.pdfService = pdfService;
    this.fileService = fileService;
  }

  @ApiOperation(value = "근로계약서 등록 메서드 - 고용주 등록")
  @PostMapping
  public ResponseEntity<Void> makeContract(@Valid @RequestBody ContractRequest request,
      @LoginUser User user) throws DocumentException, IOException {
    // PDF 생성
    byte[] pdfData = pdfService.createPdf(request, user);

    // 파일 이름 생성
    String dirName = "contracts";
    String fileName = user.getId() + "_" + request.applyId() + ".pdf";

    // GCS에 업로드
    String pdfUrl = fileService.uploadContractPdf(pdfData, dirName, fileName);
    // 파일 정보 저장
    contractService.createContract(request, pdfUrl);

    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @ApiOperation(value = "근로계약서 등록 메서드 - 근로자 서명 등록")
  @PostMapping("/employee")
  public ResponseEntity<Void> fillInEmployeeSign(@Valid @RequestBody ContractRequest request,
      @LoginUser User user) throws IOException, DocumentException {
    byte[] pdfData = fileService.getPdf(request);
    byte[] updatePdfData = pdfService.fillInEmployeeSign(pdfData, user);

    // 파일 이름 생성
    String dirName = "contracts";
    String fileName = user.getId() + "_" + request.applyId() + "update.pdf";

    String pdfUrl = fileService.uploadContractPdf(updatePdfData, dirName, fileName);

    contractService.updatePdfUrl(request, pdfUrl);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @ApiOperation(value = "근로계약서 user id 별 조회 메서드")
  @GetMapping
  public ResponseEntity<ContractResponse> getContract(@LoginUser User user) {
    return ResponseEntity.ok(contractService.getContract(user));

  }

  @ApiOperation(value = "근로계약서 id별 pdf url 반환 메서드")
  @GetMapping("/{contractId}/download")
  public ResponseEntity<String> downloadContract(@PathVariable("contractId") Long id,
      @LoginUser User user) {
    return ResponseEntity.ok(contractService.downloadContract(id, user));
  }

  @ApiOperation(value = "근로계약서 id별 image url 반환 메서드")
  @GetMapping("/{contractId}/preview")
  public ResponseEntity<String> previewContract(@PathVariable("contractId") Long id,
      @LoginUser User user) {
    return ResponseEntity.ok(contractService.previewContract(id, user));
  }
}
