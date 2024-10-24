package team18.team18_be.userInformation.service;

import java.time.LocalDate;
import java.util.NoSuchElementException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import team18.team18_be.auth.entity.User;
import team18.team18_be.config.GCS.FileUtil;
import team18.team18_be.config.GCS.GcsUploader;
import team18.team18_be.userInformation.dto.request.CompanyRequest;
import team18.team18_be.userInformation.dto.request.CompanyResponse;
import team18.team18_be.userInformation.dto.request.SignResponse;
import team18.team18_be.userInformation.dto.request.VisaRequest;
import team18.team18_be.userInformation.dto.request.VisaResponse;
import team18.team18_be.userInformation.entity.Company;
import team18.team18_be.userInformation.entity.ForeignerInformation;
import team18.team18_be.userInformation.entity.Sign;
import team18.team18_be.userInformation.repository.CompanyRepository;
import team18.team18_be.userInformation.repository.ForeignerInformationRepository;
import team18.team18_be.userInformation.repository.SignRepository;

@Service
public class UserInformationService {

  private final ForeignerInformationRepository foreignerInformationRepository;
  private final CompanyRepository companyRepository;
  private final SignRepository signRepository;
  private final GcsUploader gcsUploader;
  private final FileUtil fileUtil;


  public UserInformationService(ForeignerInformationRepository foreignerInformationRepository,
      CompanyRepository companyRepository, SignRepository signRepository, GcsUploader gcsUploader,
      FileUtil fileUtil) {
    this.foreignerInformationRepository = foreignerInformationRepository;
    this.companyRepository = companyRepository;
    this.signRepository = signRepository;
    this.gcsUploader = gcsUploader;
    this.fileUtil = fileUtil;
  }

  public void createCompany(CompanyRequest companyRequest, MultipartFile logoImage, User user) {
    byte[] imageFile = fileUtil.safelyGetBytes(logoImage)
        .orElseThrow(() -> new IllegalArgumentException("multipart 파일을 읽지 못하였습니다."));
    String storedFileName = gcsUploader.upload(imageFile, "companyLogo",
            user.getId().toString() + logoImage.getOriginalFilename())
        .orElseThrow(() -> new NoSuchElementException("파일 업로드에 실패했습니다."));
    Company company = new Company(companyRequest.name(), companyRequest.industryOccupation(),
        companyRequest.brand(), companyRequest.revenuePerYear(), storedFileName, user);
    companyRepository.save(company);
  }

  public CompanyResponse findCompany(User user) {
    Company company = companyRepository.findByUser(user);
    CompanyResponse companyResponse = new CompanyResponse(company.getId(), company.getName(),
        company.getIndustryOccupation(), company.getBrand(), company.getRevenuePerYear(),
        company.getLogoImage());
    return companyResponse;
  }

  public void fillInVisa(VisaRequest visaRequest, User user) {
    LocalDate visaGenerateDate = LocalDate.parse(visaRequest.visaGenerateDate());
    LocalDate visaExpiryDate = visaGenerateDate.plusYears(10);
    ForeignerInformation newForeignerInformation = new ForeignerInformation(user.getId(), visaRequest.foreignerIdNumber(),
        visaGenerateDate, visaExpiryDate, user);
    foreignerInformationRepository.save(newForeignerInformation);
  }

  public VisaResponse findVisa(User user) {
    ForeignerInformation foreignerInformation = foreignerInformationRepository.findByUser(user);
    String visaGenerateDate = foreignerInformation.getVisaGenerateDate().toString();
    String visaExpiryDate = foreignerInformation.getVisaExpiryDate().toString();
    VisaResponse visaResponse = new VisaResponse(foreignerInformation.getForeignerIdNumber(),
        visaGenerateDate, visaExpiryDate);
    return visaResponse;
  }

  public void fillInSign(MultipartFile imageUrl, User user) {
    byte[] imageFile = fileUtil.safelyGetBytes(imageUrl)
        .orElseThrow(() -> new IllegalArgumentException("multipart 파일을 읽지 못하였습니다."));
    String storedFileName = gcsUploader.upload(imageFile, "Sign",
            user.getId().toString() + imageUrl.getOriginalFilename())
        .orElseThrow(() -> new NoSuchElementException("파일 업로드에 실패했습니다."));
    Sign newSign = new Sign(storedFileName, user);
    signRepository.save(newSign);
  }

  public SignResponse findSign(User user) {
    Sign sign = signRepository.findByUser(user);
    SignResponse signResponse = new SignResponse(sign.getImageUrl());
    return signResponse;
  }

}
