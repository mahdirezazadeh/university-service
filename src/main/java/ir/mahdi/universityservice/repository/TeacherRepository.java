package ir.mahdi.universityservice.repository;

import ir.mahdi.universityservice.domain.Teacher;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    @EntityGraph(attributePaths = {"roles", "roles.operations"})
    Optional<Teacher> findTeacherByUsername(String username);

    <P> P findTeacherByUsername(String username, Class<P> clazz);

    @Query("select t from Teacher t where t.isActive = ?1 and t.isConfirmed = ?2 and t.isDeleted = ?3")
    List<Teacher> findTeacherByActiveAndConfirmedAndDeleted(boolean isActive, boolean isConfirmed, boolean isDeleted);
}
