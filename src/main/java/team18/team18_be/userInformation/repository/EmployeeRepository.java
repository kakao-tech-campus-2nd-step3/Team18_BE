package team18.team18_be.userInformation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team18.team18_be.userInformation.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {

}
