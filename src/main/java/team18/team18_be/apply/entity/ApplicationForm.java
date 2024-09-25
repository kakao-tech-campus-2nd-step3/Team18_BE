package team18.team18_be.apply.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table
public class ApplicationForm {
  @Id
  private Long resumeId;
  @Size(max = 10, message = "최대 10글자까지 입력 가능합니다.")
  private String name;
  private String address;
  @Pattern(regexp = "^010\\d{8}$", message = "-없이 입력해주세요. ex) 01012345678")
  private String phoneNumber;
  @Size(max = 500, message = "500글자 내로 입력해주세요.")
  private String applyMotivation;

  public ApplicationForm(Long resumeId, String name, String address, String phoneNumber,
    String applyMotivation) {
    this.resumeId = resumeId;
    this.name = name;
    this.address = address;
    this.phoneNumber = phoneNumber;
    this.applyMotivation = applyMotivation;
  }
}
