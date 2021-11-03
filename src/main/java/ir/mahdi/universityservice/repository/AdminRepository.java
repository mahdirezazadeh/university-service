package ir.mahdi.universityservice.repository;

import ir.mahdi.universityservice.domain.Admin;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    @EntityGraph(attributePaths = {"roles", "roles.operations"})
    Optional<Admin> findAdminByUsername(String username);

    <P> P findAdminByUsername(String username, Class<P> clazz);
}
