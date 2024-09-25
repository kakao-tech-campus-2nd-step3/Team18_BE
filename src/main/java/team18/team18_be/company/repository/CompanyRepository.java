package team18.team18_be.company.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team18.team18_be.company.entity.Company;

public interface CompanyRepository extends JpaRepository<Company,Long> {

}
