package team18.team18_be.apply.dto.request;

public record ApplyRequest(
  String name,
  String address,
  String phoneNumber,
  String applyMotivation
) {

}
