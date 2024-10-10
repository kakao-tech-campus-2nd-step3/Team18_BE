package team18.team18_be.userInformation.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import team18.team18_be.auth.entity.User;

@Table
@Entity
public class Company {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @NotBlank
  private String companyName;
  @NotBlank
  private String industryOccupation;
  private String brand;
  @NotBlank
  private String revenuePerYear;
  private String logo;

  @ManyToOne
  @JoinColumn(name="user_id")
  private User user;
  public Company() {
  }
  public Company(String companyName, String industryOccupation, String brand,
      String revenuePerYear, String logo, User user) {
    this.companyName = companyName;
    this.industryOccupation = industryOccupation;
    this.brand = brand;
    this.revenuePerYear = revenuePerYear;
    this.logo = logo;
    this.user=user;
  }

  public Company(String companyName, String industryOccupation, String brand,
      String revenuePerYear, String logo) {
    this.companyName = companyName;
    this.industryOccupation = industryOccupation;
    this.brand = brand;
    this.revenuePerYear = revenuePerYear;
    this.logo = logo;
  }

  public String getCompanyName() {
    return companyName;
  }

  public String getIndustryOccupation() {
    return industryOccupation;
  }

  public String getBrand() {
    return brand;
  }

  public String getRevenuePerYear() {
    return revenuePerYear;
  }

  public String getLogo() {
    return logo;
  }
}
