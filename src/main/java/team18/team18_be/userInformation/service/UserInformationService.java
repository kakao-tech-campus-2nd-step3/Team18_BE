package team18.team18_be.userInformation.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import team18.team18_be.auth.entity.User;
import team18.team18_be.userInformation.dto.request.CompanyRequest;
import team18.team18_be.userInformation.dto.request.VisaRequest;
import team18.team18_be.userInformation.dto.request.VisaResponse;
import team18.team18_be.userInformation.entity.Employee;
import team18.team18_be.userInformation.repository.EmployeeRepository;

@Service
public class UserInformationService {

  private final EmployeeRepository employeeRepository;

  public UserInformationService(EmployeeRepository employeeRepository) {
    this.employeeRepository = employeeRepository;
  }

  public void createCompany(CompanyRequest companyRequest) {
    MultipartFile logoFile = companyRequest.logo();

  }

  public void fillInVisa(VisaRequest visaRequest, User user) {
    Employee employee = employeeRepository.findByUser(user);
    Employee newEmployee = new Employee(employee.getId(), visaRequest.foreginerNumber(),
        visaRequest.visaGenereteDate(), user);
    employeeRepository.save(newEmployee);
  }

  public void fillInSign(MultipartFile file) {
  }

  public VisaResponse findVisa(User user) {
    Employee employee = employeeRepository.findByUser(user);
    VisaResponse visaResponse = new VisaResponse(employee.getForeginerNumber(),
        employee.getVisaGenerateDate());
    return visaResponse;
  }
}
