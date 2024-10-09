package team18.team18_be.recruitment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team18.team18_be.recruitment.entity.Recruitment;

public interface RecruitmentRepository extends JpaRepository<Recruitment,Long> {

}
