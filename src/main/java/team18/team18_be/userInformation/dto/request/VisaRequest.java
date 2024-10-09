package team18.team18_be.userInformation.dto.request;

import java.time.LocalDateTime;

public record VisaRequest(String foreginerNumber, LocalDateTime visaGenereteDate) {

}
