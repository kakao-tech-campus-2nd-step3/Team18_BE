package team18.team18_be.userInformation.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import team18.team18_be.auth.entity.User;
import team18.team18_be.userInformation.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
  Employee findByUser(User user);

}
