package ir.mahdi.universityservice.repository;

import ir.mahdi.universityservice.domain.base.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByUsername(String username);
}
