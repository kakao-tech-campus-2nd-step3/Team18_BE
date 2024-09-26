package team18.team18_be.userInformation.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Table
@Entity
public class Company {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long companyId;
  @NotBlank
  private String companyName;
  @NotBlank
  private String industryOccupation;
  private String brand;
  @NotBlank
  private Long revenuePerYear;
  private String logo;
  private String hompageUrl;

  public Company(Long companyId, String companyName, String industryOccupation, String brand,
                 Long revenuePerYear, String logo, String hompageUrl) {
    this.companyId = companyId;
    this.companyName = companyName;
    this.industryOccupation = industryOccupation;
    this.brand = brand;
    this.revenuePerYear = revenuePerYear;
    this.logo = logo;
    this.hompageUrl = hompageUrl;
  }
}
