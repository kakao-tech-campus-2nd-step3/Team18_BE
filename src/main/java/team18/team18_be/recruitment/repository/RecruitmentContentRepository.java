package team18.team18_be.recruitment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team18.team18_be.recruitment.entity.RecruitmentContent;

public interface RecruitmentContentRepository extends JpaRepository<RecruitmentContent,Long> {

  RecruitmentContent findByRecruitmentId(Long id);
}
