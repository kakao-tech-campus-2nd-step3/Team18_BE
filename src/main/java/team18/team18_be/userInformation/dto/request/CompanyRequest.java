package team18.team18_be.userInformation.dto.request;

import org.springframework.web.multipart.MultipartFile;

public record CompanyRequest(String company, String industryOccupation, String brand,
                             String revenuePerYear) {

}
