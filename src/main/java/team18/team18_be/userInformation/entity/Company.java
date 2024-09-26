package team18.team18_be.userInformation.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table
@Entity
public class Company {

  @Id
  private Long companyId;
  private String companyName;
  private String industryOccupation;
  private String brand;
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
