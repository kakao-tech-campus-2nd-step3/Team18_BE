package team18.team18_be.auth.dto.request;

import jakarta.validation.constraints.Pattern;

public record UserTypeRequest(

        @Pattern(regexp = "employee|employer", message = "요청한 사용자 유형 값이 유효하지 않습니다.")
        String type
) {

}
