package team18.team18_be.contract.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import team18.team18_be.apply.entity.Apply;
import team18.team18_be.apply.repository.ApplyRepository;
import team18.team18_be.contract.dto.request.ContractRequest;
import team18.team18_be.contract.entity.Contract;
import team18.team18_be.contract.repository.ContractRepository;
import team18.team18_be.exception.NotFoundException;

@Service
public class ContractService {

  private final ContractRepository contractRepository;
  private final ApplyRepository applyRepository;

  public ContractService(ContractRepository contractRepository, ApplyRepository applyRepository) {
    this.contractRepository = contractRepository;
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

  @Transactional
  public void updatePdfUrl(ContractRequest request, String pdfUrl) {
    Contract contract = contractRepository.findByApplyId(request.applyId())
        .orElseThrow(() -> new NotFoundException("해당 appliyId의 Contract가 존재하지 않습니다."));

    contract.updatePdfFileUrl(pdfUrl);
  }

  @Transactional
  public void updateImageUrl(ContractRequest request, String imageUrl) {
    Contract contract = contractRepository.findByApplyId(request.applyId())
        .orElseThrow(() -> new NotFoundException("해당 appliyId의 Contract가 존재하지 않습니다."));

    contract.updateImageFileUrl(imageUrl);
  }

}
