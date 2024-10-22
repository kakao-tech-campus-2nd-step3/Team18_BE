package team18.team18_be.contract.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import team18.team18_be.apply.entity.ApplicationForm;
import team18.team18_be.apply.entity.Apply;
import team18.team18_be.auth.entity.User;

@Entity(name = "contracts")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Contract {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private int salary;
  private String workingHours;
  private String dayOff;
  private String annualPaidLeave;
  private String workingPlace;
  private String responsibilities;
  private String imageFileUrl;
  private String pdfFileUrl;
  private String rule;
  @ManyToOne
  @JoinColumn(name = "applyId")
  private Apply apply;

  public String getPdfFileUrl() {
    return this.pdfFileUrl;
  }

  public void updatePdfFileUrl(String pdfFileUrl) {
    this.pdfFileUrl = pdfFileUrl;
  }
}
