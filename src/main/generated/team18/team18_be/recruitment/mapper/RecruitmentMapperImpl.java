package team18.team18_be.recruitment.mapper;

import java.util.Date;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import team18.team18_be.recruitment.dto.request.RecruitmentRequest;
import team18.team18_be.recruitment.dto.response.RecruitmentResponse;
import team18.team18_be.recruitment.entity.Recruitment;
import team18.team18_be.recruitment.entity.RecruitmentContent;
import team18.team18_be.userInformation.entity.Company;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-12T02:16:28+0900",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 21.0.3 (Eclipse Adoptium)"
)
@Component
public class RecruitmentMapperImpl implements RecruitmentMapper {

    @Override
    public Recruitment toRecruitment(String koreanTitle, String vietnameseTitle, RecruitmentRequest recruitmentRequest, RecruitmentContent recruitmentContent, Company company, Boolean hiring, Date uploadDate) {
        if ( koreanTitle == null && vietnameseTitle == null && recruitmentRequest == null && recruitmentContent == null && company == null && hiring == null && uploadDate == null ) {
            return null;
        }

        Recruitment recruitment = new Recruitment();

        if ( recruitmentRequest != null ) {
            recruitment.setCompanySize( recruitmentRequest.companySize() );
            recruitment.setArea( recruitmentRequest.area() );
            recruitment.setSalary( recruitmentRequest.salary() );
            recruitment.setWorkDuration( recruitmentRequest.workDuration() );
            recruitment.setWorkDays( recruitmentRequest.workDays() );
            recruitment.setWorkType( recruitmentRequest.workType() );
            recruitment.setWorkHours( recruitmentRequest.workHours() );
            recruitment.setRequestedCareer( recruitmentRequest.requestedCareer() );
            recruitment.setMajorBusiness( recruitmentRequest.majorBusiness() );
            recruitment.setEligibilityCriteria( recruitmentRequest.eligibilityCriteria() );
            recruitment.setPreferredConditions( recruitmentRequest.preferredConditions() );
            recruitment.setEmployerName( recruitmentRequest.employerName() );
            recruitment.setCompanyName( recruitmentRequest.companyName() );
        }
        recruitment.setKoreanTitle( koreanTitle );
        recruitment.setVietnameseTitle( vietnameseTitle );
        recruitment.setRecruitmentContent( recruitmentContent );
        recruitment.setCompany( company );
        recruitment.setHiring( hiring );
        recruitment.setUploadDate( uploadDate );

        return recruitment;
    }

    @Override
    public RecruitmentResponse toRecruitmentResponse(Recruitment recruitment, RecruitmentContent recruitmentContent) {
        if ( recruitment == null && recruitmentContent == null ) {
            return null;
        }

        String koreanTitle = null;
        String vietnameseTitle = null;
        String companySize = null;
        String area = null;
        Long salary = null;
        String workDuration = null;
        String workDays = null;
        String workType = null;
        String workHours = null;
        String requestedCareer = null;
        String majorBusiness = null;
        String eligibilityCriteria = null;
        String preferredConditions = null;
        String employerName = null;
        if ( recruitment != null ) {
            koreanTitle = recruitment.getKoreanTitle();
            vietnameseTitle = recruitment.getVietnameseTitle();
            companySize = recruitment.getCompanySize();
            area = recruitment.getArea();
            salary = recruitment.getSalary();
            workDuration = recruitment.getWorkDuration();
            workDays = recruitment.getWorkDays();
            workType = recruitment.getWorkType();
            workHours = recruitment.getWorkHours();
            requestedCareer = recruitment.getRequestedCareer();
            majorBusiness = recruitment.getMajorBusiness();
            eligibilityCriteria = recruitment.getEligibilityCriteria();
            preferredConditions = recruitment.getPreferredConditions();
            employerName = recruitment.getEmployerName();
        }
        String koreanDetailedDescription = null;
        String vietnameseDetailedDescription = null;
        if ( recruitmentContent != null ) {
            koreanDetailedDescription = recruitmentContent.getKoreanDetailedDescription();
            vietnameseDetailedDescription = recruitmentContent.getVietnameseDetailedDescription();
        }

        RecruitmentResponse recruitmentResponse = new RecruitmentResponse( koreanTitle, vietnameseTitle, companySize, area, salary, workDuration, workDays, workType, workHours, requestedCareer, majorBusiness, eligibilityCriteria, preferredConditions, employerName, koreanDetailedDescription, vietnameseDetailedDescription );

        return recruitmentResponse;
    }
}
