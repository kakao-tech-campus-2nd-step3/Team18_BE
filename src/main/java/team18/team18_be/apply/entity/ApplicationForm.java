package team18.team18_be.apply.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table
public class ApplicationForm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long applicationFormId;
    @Size(max = 10, message = "최대 10글자까지 입력 가능합니다.")
    @NotBlank
    private String name;
    @NotBlank
    private String address;
    @Pattern(regexp = "^010\\d{8}$", message = "-없이 입력해주세요. ex) 01012345678")
    @NotBlank
    private String phoneNumber;
    @Size(max = 500, message = "500글자 내로 입력해주세요.")
    @NotBlank
    private String applyMotivation;
    private Long resumeId;


    public ApplicationForm(Long resumeId, String name, String address, String phoneNumber,
                           String applyMotivation) {
        this.resumeId = resumeId;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.applyMotivation = applyMotivation;
    }
}
