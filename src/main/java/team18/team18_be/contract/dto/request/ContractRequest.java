package team18.team18_be.contract.dto.request;

import jakarta.validation.constraints.Pattern;

public record ContractRequest(
  String companyName,
  String address,
  @Pattern(regexp = "^010-\\d{4}-\\d{4}$", message = "전화번호 형식이 아닙니다. 예) 010-0000-0000")
  String phoneNumber,
  String contractPeriod
) {

}
