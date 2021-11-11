package ir.mahdi.universityservice.repository;

import ir.mahdi.universityservice.domain.Course;
import ir.mahdi.universityservice.domain.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ExamRepository extends JpaRepository<Exam, Long> {

    @Query("select e from Exam e where e.course = ?1 and e.isDeleted = false")
    List<Exam> findExamByCourse(Course course);

}
