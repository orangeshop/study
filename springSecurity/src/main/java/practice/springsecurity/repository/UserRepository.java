package practice.springsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import practice.springsecurity.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
