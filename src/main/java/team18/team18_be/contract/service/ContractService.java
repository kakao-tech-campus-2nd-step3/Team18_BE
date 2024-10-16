package team18.team18_be.contract.service;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import java.io.ByteArrayOutputStream;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import team18.team18_be.auth.entity.User;
import team18.team18_be.contract.dto.request.ContractRequest;
import team18.team18_be.contract.dto.response.ContractResponse;
import team18.team18_be.contract.repository.ContractRepository;

import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class ContractService {

  private final ContractRepository contractRepository;
  private final ContractPdfService pdfService;
  private final ContractFileUploadService fileUploadService;

  public ContractService(ContractRepository contractRepository, ContractPdfService pdfService,
      ContractFileUploadService fileUploadService) {
    this.contractRepository = contractRepository;
    this.pdfService = pdfService;
    this.fileUploadService = fileUploadService;
  }

  public void createAndUploadContract(ContractRequest request, User user)
      throws DocumentException, IOException {

    byte[] pdf = pdfService.createPdf(request);

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
