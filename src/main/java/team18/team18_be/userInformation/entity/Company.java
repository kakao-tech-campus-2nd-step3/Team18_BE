package team18.team18_be.userInformation.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Table
@Entity
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long companyId;
    @NotBlank
    private final String companyName;
    @NotBlank
    private final String industryOccupation;
    private final String brand;
    @NotBlank
    private final Long revenuePerYear;
    private final String logo;
    private final String hompageUrl;

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
