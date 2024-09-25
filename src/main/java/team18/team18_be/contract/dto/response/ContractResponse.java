package team18.team18_be.contract.dto.response;

public record ContractResponse(
    Long id,
    String companyName,
    String address,
    String phoneNumber,
    String contractPeriod
) {

}
