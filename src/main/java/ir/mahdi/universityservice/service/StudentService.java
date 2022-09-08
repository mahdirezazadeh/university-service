package ir.mahdi.universityservice.service;

import ir.mahdi.universityservice.base.service.BaseService;
import ir.mahdi.universityservice.domain.Course;
import ir.mahdi.universityservice.domain.Exam;
import ir.mahdi.universityservice.domain.Student;

import java.util.Collection;
import java.util.List;

public interface StudentService extends BaseService<Student, Long> {

    Student findByUsername(String username);

    List<Student> findOtherStudents(Collection<Student> studentsInCourse);

    void signup(Student student);

    List<Course> findCoursesByCurrentStudent();

    Course findCourseById(long id);

    List<Exam> findCurrentStudentUndoneExamsForCourse(Course course);
}
