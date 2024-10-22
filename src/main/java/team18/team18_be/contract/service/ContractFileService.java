package team18.team18_be.contract.service;

import io.jsonwebtoken.io.IOException;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import team18.team18_be.auth.entity.User;
import team18.team18_be.config.GCS.GcsUploader;
import team18.team18_be.contract.dto.request.ContractRequest;
import team18.team18_be.contract.entity.Contract;
import team18.team18_be.contract.repository.ContractRepository;
import team18.team18_be.exception.FileDownloadException;
import team18.team18_be.exception.NotFoundException;
import team18.team18_be.userInformation.entity.Sign;
import team18.team18_be.userInformation.repository.SignRepository;

@Service
public class ContractFileService {

  private final GcsUploader gcsUploader;
  private final SignRepository signRepository;
  private final ContractRepository contractRepository;
  private final RestTemplate restTemplate;

  public ContractFileService(GcsUploader gcsUploader, SignRepository signRepository,
      ContractRepository contractRepository, RestTemplate restTemplate) {
    this.gcsUploader = gcsUploader;
    this.signRepository = signRepository;
    this.contractRepository = contractRepository;
    this.restTemplate = restTemplate;
  }

  public String uploadContractPdf(byte[] pdfData, String dirName, String fileName) {
    Optional<String> uploadUrl = gcsUploader.upload(pdfData, dirName, fileName);
    System.out.println(uploadUrl.orElseThrow(() -> new IOException("url을 찾을 수 없습니다.")));
    return uploadUrl.orElseThrow(() -> new IOException("url을 찾을 수 없습니다."));
  }

  public byte[] getSignImage(User user) {
    Sign sign = signRepository.findByUser(user);

    ResponseEntity<byte[]> response = restTemplate.getForEntity(sign.getImageUrl(), byte[].class);

    if (response.getStatusCode().is2xxSuccessful()) {
      return response.getBody();
    } else {
      throw new FileDownloadException("파일 다운로드 실패");
    }
  }

  public byte[] getPdf(ContractRequest request) {
    Contract contract = contractRepository.findByApplyId(request.applyId())
        .orElseThrow(() -> new NotFoundException("해당 applyId의 Contract 객체를 찾을 수 없습니다."));

    ResponseEntity<byte[]> response = restTemplate.getForEntity(contract.getPdfFileUrl(),
        byte[].class);

    if (response.getStatusCode().is2xxSuccessful()) {
      return response.getBody();
    } else {
      throw new FileDownloadException("파일 다운로드 실패");
    }
  }

  public String getPdfUrl(Long applyId) {
    Contract contract = contractRepository.findByApplyId(applyId)
        .orElseThrow(() -> new NotFoundException("해당 applyId의 Contract 객체를 찾을 수 없습니다."));

    return contract.getPdfFileUrl();
  }

  public String getImageUrl(Long applyId) {
    Contract contract = contractRepository.findByApplyId(applyId)
        .orElseThrow(() -> new NotFoundException("해당 applyId의 Contract 객체를 찾을 수 없습니다."));

    return contract.getImageFileUrl();
  }
}
