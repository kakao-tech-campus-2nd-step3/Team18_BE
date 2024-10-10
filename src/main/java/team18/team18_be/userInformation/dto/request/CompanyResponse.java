package team18.team18_be.userInformation.dto.request;

import org.springframework.web.multipart.MultipartFile;

public record CompanyResponse(String company, String industryOccupation, String brand,
                              String revenuePerYear, String logo) {
}
