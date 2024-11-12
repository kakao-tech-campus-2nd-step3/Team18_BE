package team18.team18_be.contract.service;

import com.itextpdf.text.DocumentException;
import jakarta.transaction.Transactional;
import java.io.IOException;
import org.springframework.stereotype.Service;
import team18.team18_be.apply.ApplyStatusEnum.ApplyStatus;
import team18.team18_be.apply.entity.Apply;
import team18.team18_be.apply.repository.ApplyRepository;
import team18.team18_be.auth.entity.User;
import team18.team18_be.common.FileService;
import team18.team18_be.common.PdfService;
import team18.team18_be.contract.dto.request.ContractRequest;
import team18.team18_be.contract.dto.response.ContractFileResponse;
import team18.team18_be.contract.dto.response.ContractResponse;
import team18.team18_be.contract.entity.Contract;
import team18.team18_be.contract.repository.ContractRepository;
import team18.team18_be.exception.NotFoundException;

@Service
public class ContractService {

  private final ContractRepository contractRepository;
  private final ApplyRepository applyRepository;
  private final FileService fileService;
  private final PdfService pdfService;

  public ContractService(ContractRepository contractRepository, ApplyRepository applyRepository,
      FileService fileService, PdfService pdfService) {
    this.contractRepository = contractRepository;
    this.applyRepository = applyRepository;
    this.fileService = fileService;
    this.pdfService = pdfService;
  }

  public void handleEmployerContractCreation(ContractRequest request, User user)
      throws DocumentException, IOException {

    String dirName = "contracts";
    String fileName = user.getId() + "_" + request.applyId() + ".pdf";
    String fileNameV = user.getId() + "_" + request.applyId() + "V.pdf";

    byte[] pdfData = pdfService.createPdf(request, user, "contract.pdf");
    String pdfUrl = fileService.uploadContractPdf(pdfData, dirName, fileName);

    byte[] pdfDataV = pdfService.createPdf(request, user, "contract_vietnam.pdf");
    String pdfUrlV = fileService.uploadContractPdf(pdfDataV, dirName, fileNameV);

    createContract(request, pdfUrl, pdfUrlV);
  }

  @Transactional
  public void handleEmployeeSignature(ContractRequest request, User user)
      throws DocumentException, IOException {

    String dirName = "contracts";
    String pdfFileName = user.getId() + "_" + request.applyId() + "update.pdf";
    String pdfFileNameV = user.getId() + "_" + request.applyId() + "updateV.pdf";
    String imageFileName = user.getId() + "_" + request.applyId() + "update.png";
    String imageFileNameV = user.getId() + "_" + request.applyId() + "updateV.png";

    byte[] pdfData = fileService.getPdf(request);
    byte[] updatedPdfData = pdfService.fillInEmployeeSign(pdfData, user);
    byte[] image = pdfService.convertPdfToImage(updatedPdfData);
    String pdfUrl = fileService.uploadContractPdf(updatedPdfData, dirName, pdfFileName);
    String imageUrl = fileService.uploadContractPdf(image, dirName, imageFileName);

    byte[] pdfDataV = fileService.getPdfV(request);
    byte[] updatedPdfDataV = pdfService.fillInEmployeeSign(pdfDataV, user);
    byte[] imageV = pdfService.convertPdfToImage(updatedPdfDataV);
    String pdfUrlV = fileService.uploadContractPdf(updatedPdfDataV, dirName, pdfFileNameV);
    String imageUrlV = fileService.uploadContractPdf(imageV, dirName, imageFileNameV);

    updateContractFiles(request, pdfUrl, imageUrl, pdfUrlV, imageUrlV);
  }

  public void createContract(ContractRequest request, String pdfUrl, String pdfUrlV) {

    Apply apply = applyRepository.findById(request.applyId())
        .orElseThrow(() -> new NotFoundException("해당 applyId가 존재하지 않습니다."));

    contractRepository.save(
        Contract.builder()
            .salary(request.salary())
            .workingPlace(request.workingPlace())
            .workingHours(request.workingHours())
            .responsibilities(request.responsibilities())
            .annualPaidLeave(request.annualPaidLeave())
            .dayOff(request.dayOff())
            .rule(request.rule())
            .imageFileUrl(null)
            .pdfFileUrl(pdfUrl)
            .imageFileUrlV(null)
            .pdfFileUrlV(pdfUrlV)
            .apply(apply).build()
    );
  }

  @Transactional
  private void updateContractFiles(ContractRequest request, String pdfUrl, String imageUrl,
      String pdfUrlV, String imageUrlV) {
    Apply apply = applyRepository.findById(request.applyId())
        .orElseThrow(() -> new NotFoundException("해당 applyId가 존재하지 않습니다."));
    Contract contract = contractRepository.findByApplyId(request.applyId())
        .orElseThrow(() -> new NotFoundException("해당 applyId의 Contract가 존재하지 않습니다."));
    ApplyStatus status = ApplyStatus.HIRED;
    applyRepository.save(
        new Apply(apply.getId(), status.getKoreanName(), apply.getUser(), apply.getRecruitment()));
    contract.updatePdfFileUrl(pdfUrl);
    contract.updateImageFileUrl(imageUrl);
    contract.updatePdfFileUrlV(pdfUrlV);
    contract.updateImageFileUrlV(imageUrlV);
  }

  public ContractFileResponse getContractPdfUrl(Long applyId) {
    Contract contract = contractRepository.findByApplyId(applyId)
        .orElseThrow(() -> new NotFoundException("해당 applyId의 Contract 객체를 찾을 수 없습니다."));
    return new ContractFileResponse(contract.getPdfFileUrl(), contract.getPdfFileUrlV());
  }

  public ContractFileResponse getContractImageUrl(Long applyId) {
    Contract contract = contractRepository.findByApplyId(applyId)
        .orElseThrow(() -> new NotFoundException("해당 applyId의 Contract 객체를 찾을 수 없습니다."));
    return new ContractFileResponse(contract.getImageFileUrl(), contract.getImageFileUrlV());
  }

  public ContractResponse getContract(Long applyId) {
    Contract contract = contractRepository.findByApplyId(applyId)
        .orElseThrow(() -> new NotFoundException("해당 applyId의 Contract 객체를 찾을 수 없습니다."));
    return new ContractResponse(
        contract.getSalary(),
        contract.getWorkingHours(),
        contract.getDayOff(),
        contract.getAnnualPaidLeave(),
        contract.getWorkingPlace(),
        contract.getResponsibilities(),
        contract.getRule()
    );
  }

}
