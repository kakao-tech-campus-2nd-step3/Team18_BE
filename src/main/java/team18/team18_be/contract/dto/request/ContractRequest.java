package team18.team18_be.contract.dto.request;

public record ContractRequest(
        int salary,
        ContractPeriod contractPeriod,
        String dayOff,
        String annualPaidLeave,
        String workingPlace,
        String responsibilities,
        String rule,
        Long employeeId
) {

}
