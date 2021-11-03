package ir.mahdi.universityservice.repository;

import ir.mahdi.universityservice.domain.Teacher;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    @EntityGraph(attributePaths = {"roles", "roles.operations"})
    Optional<Teacher> findTeacherByUsername(String username);

    <P> P findTeacherByUsername(String username, Class<P> clazz);
}
