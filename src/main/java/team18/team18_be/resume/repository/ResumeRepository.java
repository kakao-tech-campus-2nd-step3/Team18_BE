package team18.team18_be.resume.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team18.team18_be.resume.entity.Resume;

public interface ResumeRepository extends JpaRepository<Resume, Long> {

  Resume findByEmployeeId(Long employeeId);
}
