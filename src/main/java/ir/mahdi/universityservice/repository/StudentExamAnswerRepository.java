package ir.mahdi.universityservice.repository;

import ir.mahdi.universityservice.domain.Course;
import ir.mahdi.universityservice.domain.Exam;
import ir.mahdi.universityservice.domain.Student;
import ir.mahdi.universityservice.domain.StudentExamAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentExamAnswerRepository extends JpaRepository<StudentExamAnswer, Long> {

    @Query("select s.exam from StudentExamAnswer s where s.student = ?1 and s.exam.course = ?2")
    List<Exam> findAllExamsByStudentAndCourse(Student student, Course course);

    @Query("select s from StudentExamAnswer s where s.exam = ?1 and s.student = ?2")
    StudentExamAnswer findByExamAndStudent(Exam exam, Student student);
}
