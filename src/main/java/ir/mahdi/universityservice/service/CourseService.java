package ir.mahdi.universityservice.service;

import ir.mahdi.universityservice.base.service.BaseService;
import ir.mahdi.universityservice.domain.Course;
import ir.mahdi.universityservice.domain.Student;
import ir.mahdi.universityservice.domain.Teacher;

import java.util.List;
import java.util.Set;

public interface CourseService extends BaseService<Course, Long> {
    List<Course> findCoursesByTeacher(Teacher teacher);

    List<Course> findCoursesByStudent(Student student);

    List<Teacher> findAvailableTeachers();

    List<Student> findOtherStudents(Set<Student> studentsInCourse);

    void setTeacherForCourse(long teacherId, long courseId);

    void addOrRemoveStudent(long studentId, long courseId);
}
