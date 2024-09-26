package team18.team18_be.userInformation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team18.team18_be.auth.entity.User;

public interface UserInformationRepository extends JpaRepository<User, Long> {

}
