package team18.team18_be.contract.service;

import org.springframework.stereotype.Service;
import team18.team18_be.contract.dto.request.ContractRequest;
import team18.team18_be.contract.dto.response.ContractResponse;
import team18.team18_be.contract.repository.ContractRepository;

@Service
public class ContractService {
    private final ContractRepository contractRepository;

    public ContractService(ContractRepository contractRepository) {
        this.contractRepository = contractRepository;
    }

    public void makeContract(ContractRequest request, User user) {
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
