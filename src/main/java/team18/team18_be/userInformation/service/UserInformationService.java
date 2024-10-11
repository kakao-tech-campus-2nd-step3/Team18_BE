package team18.team18_be.userInformation.service;

import java.util.NoSuchElementException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import team18.team18_be.auth.entity.User;
import team18.team18_be.config.GCS.GcsUploader;
import team18.team18_be.userInformation.dto.request.CompanyRequest;
import team18.team18_be.userInformation.dto.request.CompanyResponse;
import team18.team18_be.userInformation.dto.request.SignResponse;
import team18.team18_be.userInformation.dto.request.VisaRequest;
import team18.team18_be.userInformation.dto.request.VisaResponse;
import team18.team18_be.userInformation.entity.Company;
import team18.team18_be.userInformation.entity.Employee;
import team18.team18_be.userInformation.entity.Sign;
import team18.team18_be.userInformation.repository.CompanyRepository;
import team18.team18_be.userInformation.repository.EmployeeRepository;
import team18.team18_be.userInformation.repository.SignRepository;

@Service
public class UserInformationService {

  private final EmployeeRepository employeeRepository;
  private final CompanyRepository companyRepository;
  private final SignRepository signRepository;
  private final GcsUploader gcsUploader;


  public UserInformationService(EmployeeRepository employeeRepository,
      CompanyRepository companyRepository, SignRepository signRepository, GcsUploader gcsUploader) {
    this.employeeRepository = employeeRepository;
    this.companyRepository = companyRepository;
    this.signRepository = signRepository;
    this.gcsUploader = gcsUploader;
  }

  public void createCompany(CompanyRequest companyRequest, MultipartFile logo, User user) {
    String storedFileName = gcsUploader.upload(logo, "companyLogo")
        .orElseThrow(() -> new NoSuchElementException("파일 업로드에 실패했습니다."));
    Company company = new Company(companyRequest.company(), companyRequest.industryOccupation(),
        companyRequest.brand(), companyRequest.revenuePerYear(), storedFileName, user);
    companyRepository.save(company);
  }

  public CompanyResponse findCompany(User user) {
    Company company = companyRepository.findByUser(user);
    CompanyResponse companyResponse = new CompanyResponse(company.getCompanyName(),
        company.getIndustryOccupation(), company.getBrand(), company.getRevenuePerYear(),
        company.getLogo());
    return companyResponse;
  }

  public void fillInVisa(VisaRequest visaRequest, User user) {
    Employee newEmployee = new Employee(user.getId(), visaRequest.foreginerNumber(),
        visaRequest.visaGenereteDate(), user);
    employeeRepository.save(newEmployee);
  }

  public VisaResponse findVisa(User user) {
    Employee employee = employeeRepository.findByUser(user);
    VisaResponse visaResponse = new VisaResponse(employee.getForeginerNumber(),
        employee.getVisaGenerateDate());
    return visaResponse;
  }

  public void fillInSign(MultipartFile signImg, User user) {
    String storedFileName = gcsUploader.upload(signImg, "Sign")
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
