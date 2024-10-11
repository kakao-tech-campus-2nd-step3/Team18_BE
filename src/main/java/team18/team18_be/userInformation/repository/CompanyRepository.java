package team18.team18_be.userInformation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team18.team18_be.auth.entity.User;
import team18.team18_be.userInformation.entity.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {

  Company findByUser(User user);
}
