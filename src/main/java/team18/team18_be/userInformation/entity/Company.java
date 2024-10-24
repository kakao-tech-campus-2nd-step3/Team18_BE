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
  private String name;
  @NotBlank
  private String industryOccupation;
  private String brand;
  @NotBlank
  private Long revenuePerYear;
  private String logoImage;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  public Company() {
  }

  public Company(String name, String industryOccupation, String brand,
      Long revenuePerYear, String logoImage, User user) {
    this.name = name;
    this.industryOccupation = industryOccupation;
    this.brand = brand;
    this.revenuePerYear = revenuePerYear;
    this.logoImage = logoImage;
    this.user = user;
  }

  public Long getId() {return id;}
  public String getName() {
    return name;
  }

  public String getIndustryOccupation() {
    return industryOccupation;
  }

  public String getBrand() {
    return brand;
  }

  public Long getRevenuePerYear() {
    return revenuePerYear;
  }

  public String getLogoImage() {
    return logoImage;
  }
}
