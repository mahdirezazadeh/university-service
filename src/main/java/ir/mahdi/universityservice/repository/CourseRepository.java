package ir.mahdi.universityservice.repository;

import ir.mahdi.universityservice.domain.Course;
import ir.mahdi.universityservice.domain.Student;
import ir.mahdi.universityservice.domain.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> findCourseByTeacher(Teacher teacher);

    @Query("select c from Course c where ?1 member of c.students")
    List<Course> findCoursesByStudent(Student student);
}
