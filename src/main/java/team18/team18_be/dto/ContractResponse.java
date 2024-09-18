package team18.team18_be.dto;

public record ContractResponse(
        Long id,
        String companyName,
        String address,
        String phoneNumber,
        String contractPeriod
) {
}
