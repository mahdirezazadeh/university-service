package ir.mahdi.universityservice.service;

import ir.mahdi.universityservice.base.service.BaseService;
import ir.mahdi.universityservice.domain.Course;
import ir.mahdi.universityservice.domain.Exam;
import ir.mahdi.universityservice.domain.Teacher;

import java.util.List;
import java.util.Optional;

public interface TeacherService extends BaseService<Teacher, Long> {

    Optional<Teacher> findByUsername(String username);

    List<Teacher> findAvailableTeachers();

    List<Course> findCurrentUserTeacherCourses();

    Course loadCourseById(long id);

    List<Exam> loadExamsByCourse(Course course);

    void signUpTeacher(Teacher teacher);
}
