package team18.team18_be.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team18.team18_be.auth.entity.User;

@Repository
public interface AuthRepository extends JpaRepository<User, Long> {

}
