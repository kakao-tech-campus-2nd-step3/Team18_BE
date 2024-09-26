package team18.team18_be.userInformation.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Table
@Entity
public class Sign {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long signId;
  @NotBlank
  private String signName;
  @NotBlank
  private String createDate;
  @NotNull
  private String modifiedDate;

  public Sign(Long signId, String signName, String createDate, String modifiedDate) {
    this.signId = signId;
    this.signName = signName;
    this.createDate = createDate;
    this.modifiedDate = modifiedDate;
  }
}
