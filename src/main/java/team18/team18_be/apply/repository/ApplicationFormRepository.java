package team18.team18_be.apply.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import team18.team18_be.apply.entity.ApplicationForm;
import team18.team18_be.apply.entity.Apply;

public interface ApplicationFormRepository extends JpaRepository<ApplicationForm, Long> {

  ApplicationForm findByApply(Apply apply);
}
