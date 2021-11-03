package ir.mahdi.universityservice.repository;

import ir.mahdi.universityservice.domain.Student;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    @EntityGraph(attributePaths = {"roles", "roles.operations"})
    Optional<Student> findStudentByUsername(String username);

    <P> P findStudentByUsername(String username, Class<P> clazz);
}
