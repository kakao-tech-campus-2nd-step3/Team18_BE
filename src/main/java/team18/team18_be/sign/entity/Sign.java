package team18.team18_be.sign.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table
@Entity
public class Sign {

  @Id
  private Long signId;
  private String signName;
  private String createDate;
  private String modifiedDate;

  public Sign(Long signId, String signName, String createDate, String modifiedDate) {
    this.signId = signId;
    this.signName = signName;
    this.createDate = createDate;
    this.modifiedDate = modifiedDate;
  }
}
