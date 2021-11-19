package ir.mahdi.universityservice.repository;

import ir.mahdi.universityservice.domain.Course;
import ir.mahdi.universityservice.domain.base.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question<?, ?>, Long> {

    @Query("select q from Question q where q.course = ?1")
    List<Question<?, ?>> findAllByCourse(Course course);
}
