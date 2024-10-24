package team18.team18_be.userInformation.dto.request;

public record CompanyResponse(Long CompanyId,String name, String industryOccupation, String brand,
                              Long revenuePerYear, String logo) {

}
