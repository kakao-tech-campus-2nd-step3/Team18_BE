package team18.team18_be.userInformation.service;

import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
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
    this.gcsUploader=gcsUploader;
  }

  public void createCompany(CompanyRequest companyRequest, MultipartFile logo) {
    String storedFileName = gcsUploader.upload(logo, "companyLogo")
        .orElseThrow(() -> new NoSuchElementException("파일 업로드에 실패했습니다."));
    Company company = new Company(companyRequest.company(), companyRequest.industryOccupation(),
        companyRequest.brand(), companyRequest.revenuePerYear(), storedFileName);
    companyRepository.save(company);
  }

  public CompanyResponse findCompany() {
    Company company = companyRepository.findById(Long.valueOf(1))
        .orElseThrow(() -> new NoSuchElementException("해당 companydData가 없습니다."));
    CompanyResponse companyResponse = new CompanyResponse(company.getCompanyName(),
        company.getIndustryOccupation(), company.getBrand(), company.getRevenuePerYear(),
        company.getLogo());
    return companyResponse;
  }

  //  public void fillInVisa(VisaRequest visaRequest, User user) {
//    Employee employee = employeeRepository.findByUser(user);
//    Employee newEmployee = new Employee(employee.getId(), visaRequest.foreginerNumber(),
//        visaRequest.visaGenereteDate(), user);
//    employeeRepository.save(newEmployee);
//  }
  public void fillInVisa(VisaRequest visaRequest) {
    Employee newEmployee = new Employee(visaRequest.foreginerNumber(),
        visaRequest.visaGenereteDate());
    employeeRepository.save(newEmployee);
  }

  //  public VisaResponse findVisa(User user) {
//    Employee employee = employeeRepository.findByUser(user);
//    VisaResponse visaResponse = new VisaResponse(employee.getForeginerNumber(),
//        employee.getVisaGenerateDate());
//    return visaResponse;
//  }
  public VisaResponse findVisa() {
    Employee employee = employeeRepository.findById(Long.valueOf(1))
        .orElseThrow(() -> new NoSuchElementException("해당 employee가 없습니다."));
    VisaResponse visaResponse = new VisaResponse(employee.getForeginerNumber(),
        employee.getVisaGenerateDate());
    return visaResponse;
  }


  public void fillInSign(MultipartFile signImg) {
    String storedFileName = gcsUploader.upload(signImg, "Sign")
        .orElseThrow(() -> new NoSuchElementException("파일 업로드에 실패했습니다."));
    Sign newSign = new Sign(storedFileName);
    signRepository.save(newSign);
  }

  //  public SignResponse findSign(User user) {
  //유저로 사인 엔티티 갖고오고 사인 이미지url 리턴하기
//    Sign sign = signRepository.findByUser(user);
//    SignResponse signResponse = new SignResponse(sign.getImageUrl());
//    return signResponse;
//  }
  public SignResponse findSign() {
    List<Sign> signList = signRepository.findAll();
    String init = "";
    for (Sign sign : signList) {
      init = sign.getImageUrl();
    }
    SignResponse signResponse = new SignResponse(init);
    return signResponse;
  }


}
