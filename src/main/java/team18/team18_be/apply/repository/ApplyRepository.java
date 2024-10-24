package team18.team18_be.apply.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import team18.team18_be.apply.entity.Apply;
import team18.team18_be.auth.entity.User;
import team18.team18_be.recruitment.entity.Recruitment;

public interface ApplyRepository extends JpaRepository<Apply, Long> {

  List<Apply> findByRecruitment(Recruitment recruitment);
  List<Apply> findByUser(User user);
}
