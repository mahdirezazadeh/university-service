package ir.mahdi.universityservice.repository;

import ir.mahdi.universityservice.domain.Course;
import ir.mahdi.universityservice.domain.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> findCourseByTeacher(Teacher teacher);
}
