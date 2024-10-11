package team18.team18_be.userInformation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team18.team18_be.auth.entity.User;
import team18.team18_be.userInformation.entity.Sign;

public interface SignRepository extends JpaRepository<Sign, Long> {

  Sign findByUser(User user);
}
