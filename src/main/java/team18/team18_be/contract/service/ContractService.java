package team18.team18_be.contract.service;

import com.itextpdf.text.DocumentException;
import java.io.IOException;
import org.springframework.stereotype.Service;
import team18.team18_be.apply.entity.Apply;
import team18.team18_be.apply.repository.ApplyRepository;
import team18.team18_be.auth.entity.User;
import team18.team18_be.contract.dto.request.ContractRequest;
import team18.team18_be.contract.dto.response.ContractResponse;
import team18.team18_be.contract.entity.Contract;
import team18.team18_be.contract.repository.ContractRepository;
import team18.team18_be.exception.NotFoundException;

@Service
public class ContractService {

  private final ContractRepository contractRepository;
  private final ApplyRepository applyRepository;
  private final ContractPdfService pdfService;
  private final ContractFileService fileUploadService;

  public ContractService(ContractRepository contractRepository, ContractPdfService pdfService,
      ContractFileService fileUploadService, ApplyRepository applyRepository) {
    this.contractRepository = contractRepository;
    this.pdfService = pdfService;
    this.fileUploadService = fileUploadService;
    this.applyRepository = applyRepository;
  }

  public void createContract(ContractRequest request, String pdfUrl) {

    Apply apply = applyRepository.findById(request.applyId())
        .orElseThrow(() -> new NotFoundException("해당 applyId가 존재하지 않습니다."));

    contractRepository.save(
        Contract.builder()
            .salary(request.salary())
            .workingHours(request.workingHours())
            .imageFileUrl(null)
            .pdfFileUrl(pdfUrl)
            .annualPaidLeave(request.annualPaidLeave())
            .dayOff(request.dayOff())
            .rule(request.rule())
            .apply(apply).build()
    );
  }

  public void createAndUploadContract(ContractRequest request, User user)
      throws DocumentException, IOException {

    byte[] pdf = pdfService.createPdf(request, user);

    String dirName = "contracts";
    String fileName = user.getId() + "_" + request.applyId() + ".pdf";

    fileUploadService.uploadContractPdf(pdf, dirName, fileName);
  }

  public void makeContractEmployee(User user) {
  }

  public ContractResponse getContract(User user) {
    return new ContractResponse(1L, "");
  }

  public String downloadContract(Long id, User user) {
    return "";
  }

  public String previewContract(Long id, User user) {
    return "";
  }
}
