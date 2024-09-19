package team18.team18_be.userInformation.dto.request;

public class RequestCompany {

  private String company;
  private String industryOccupation;
  private String brand;
  private String revenuePerYear;


  public RequestCompany() {
  }

  public RequestCompany(String company, String industryOccupation, String brand,
    String revenuePerYear) {
    this.company = company;
    this.industryOccupation = industryOccupation;
    this.brand = brand;
    this.revenuePerYear = revenuePerYear;
  }

  public String getCompany() {
    return company;
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
}
