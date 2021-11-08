package ir.mahdi.universityservice.repository;

import ir.mahdi.universityservice.domain.Role;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    @EntityGraph(attributePaths = {"operations"})
    Optional<Role> findByName(String name);
}
