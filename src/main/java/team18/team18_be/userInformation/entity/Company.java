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
  private final Long companyId;
  @NotBlank
  private String companyName;
  @NotBlank
  private String industryOccupation;
  private String brand;
  @NotBlank
  private Long revenuePerYear;
  private String logo;

  @ManyToOne
  @JoinColumn(name="user_id")
  private User user;

  public Company(Long companyId, String companyName, String industryOccupation, String brand,
      Long revenuePerYear, String logo, User user) {
    this.companyId = companyId;
    this.companyName = companyName;
    this.industryOccupation = industryOccupation;
    this.brand = brand;
    this.revenuePerYear = revenuePerYear;
    this.logo = logo;
    this.user=user;
  }
}
