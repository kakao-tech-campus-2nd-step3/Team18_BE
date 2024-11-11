package team18.team18_be.resume.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import team18.team18_be.auth.entity.User;
import team18.team18_be.resume.dto.request.ResumeRequest;
import team18.team18_be.resume.dto.response.ResumeAndApplyResponse;
import team18.team18_be.resume.dto.response.ResumeResponse;
import team18.team18_be.resume.entity.Resume;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-12T02:16:28+0900",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 21.0.3 (Eclipse Adoptium)"
)
@Component
public class ResumeMapperImpl implements ResumeMapper {

    @Override
    public ResumeResponse toResumeResponse(Resume resume) {
        if ( resume == null ) {
            return null;
        }

        Long resumeId = null;
        String applicantName = null;
        String address = null;
        String phoneNumber = null;
        String career = null;
        String korean = null;
        String selfIntroduction = null;

        resumeId = resume.getResumeId();
        applicantName = resume.getApplicantName();
        address = resume.getAddress();
        phoneNumber = resume.getPhoneNumber();
        career = resume.getCareer();
        korean = resume.getKorean();
        selfIntroduction = resume.getSelfIntroduction();

        ResumeResponse resumeResponse = new ResumeResponse( resumeId, applicantName, address, phoneNumber, career, korean, selfIntroduction );

        return resumeResponse;
    }

    @Override
    public ResumeAndApplyResponse toResumeAndApplyResponse(Resume resume, String motivation) {
        if ( resume == null && motivation == null ) {
            return null;
        }

        Long resumeId = null;
        String applicantName = null;
        String address = null;
        String phoneNumber = null;
        String career = null;
        String korean = null;
        String selfIntroduction = null;
        if ( resume != null ) {
            resumeId = resume.getResumeId();
            applicantName = resume.getApplicantName();
            address = resume.getAddress();
            phoneNumber = resume.getPhoneNumber();
            career = resume.getCareer();
            korean = resume.getKorean();
            selfIntroduction = resume.getSelfIntroduction();
        }
        String motivation1 = null;
        motivation1 = motivation;

        ResumeAndApplyResponse resumeAndApplyResponse = new ResumeAndApplyResponse( resumeId, applicantName, address, phoneNumber, career, korean, selfIntroduction, motivation1 );

        return resumeAndApplyResponse;
    }

    @Override
    public Resume toResume(ResumeRequest resumeRequest, User user) {
        if ( resumeRequest == null && user == null ) {
            return null;
        }

        Resume resume = new Resume();

        if ( resumeRequest != null ) {
            resume.setApplicantName( resumeRequest.applicantName() );
            resume.setAddress( resumeRequest.address() );
            resume.setPhoneNumber( resumeRequest.phoneNumber() );
            resume.setCareer( resumeRequest.career() );
            resume.setKorean( resumeRequest.korean() );
            resume.setSelfIntroduction( resumeRequest.selfIntroduction() );
        }
        resume.setUser( user );

        return resume;
    }
}
