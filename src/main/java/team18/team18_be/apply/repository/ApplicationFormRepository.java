package team18.team18_be.apply.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import team18.team18_be.apply.entity.ApplicationForm;

public interface ApplicationFormRepository extends JpaRepository<ApplicationForm, Long> {

}
