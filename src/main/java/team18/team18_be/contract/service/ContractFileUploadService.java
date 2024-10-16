package team18.team18_be.contract.service;

import org.springframework.stereotype.Service;
import team18.team18_be.config.GCS.GcsUploader;

@Service
public class ContractFileUploadService {

  private final GcsUploader gcsUploader;

  public ContractFileUploadService(GcsUploader gcsUploader) {
    this.gcsUploader = gcsUploader;
  }

  public void uploadContractPdf(byte[] pdfData, String dirName, String fileName) {
    return gcsUploader.upload(pdfData, dirName, fileName);
  }
}
