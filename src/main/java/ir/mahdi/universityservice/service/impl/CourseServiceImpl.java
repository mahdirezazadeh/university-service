package ir.mahdi.universityservice.service.impl;

import ir.mahdi.universityservice.base.BaseEntity;
import ir.mahdi.universityservice.base.service.impl.BaseServiceImpl;
import ir.mahdi.universityservice.domain.Course;
import ir.mahdi.universityservice.domain.Student;
import ir.mahdi.universityservice.domain.Teacher;
import ir.mahdi.universityservice.repository.CourseRepository;
import ir.mahdi.universityservice.service.CourseService;
import ir.mahdi.universityservice.service.StudentService;
import ir.mahdi.universityservice.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
@Transactional(readOnly = true)
public class CourseServiceImpl extends BaseServiceImpl<Course, Long, CourseRepository> implements CourseService {

    @Autowired
    public CourseServiceImpl(CourseRepository repository) {
        super(repository);
    }

    @Autowired
    private TeacherService teacherService;
    @Autowired
    private StudentService studentService;

    @Override
    @Transactional
    public Course save(Course course) {
        return super.save(course);
    }


    @Override
    @Transactional
    public List<Course> saveAll(Collection<Course> courses) {
        return super.saveAll(courses);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    @Transactional
    public List<Course> findCoursesByTeacher(Teacher teacher) {
        return repository.findCourseByTeacher(teacher);
    }

    @Override
    public List<Course> findCoursesByStudent(Student student) {
        return repository.findCoursesByStudent(student);
    }

    @Override
    public List<Teacher> findAvailableTeachers() {
        return teacherService.findAvailableTeachers();
    }

    @Override
    public List<Student> findOtherStudents(Set<Student> studentsInCourse) {
        return studentService.findOtherStudents(studentsInCourse);
    }

    @Override
    public void setTeacherForCourse(long teacherId, long courseId) {
        Optional<Course> course = findById(courseId);
        Teacher teacher = teacherService.findById(teacherId).get();
        if (course.isPresent()) {
            Course course1 = course.get();
            course1.setTeacher(teacher);
            save(course1);
        }
    }

    @Override
    public void addOrRemoveStudent(long studentId, long courseId) {
        Course course = findById(courseId).get();
        Student student = studentService.findById(studentId).get();

        long count = course.getStudents().stream().map(BaseEntity::getId).filter(id -> id == studentId).count();
        if (count == 0) {
            course.getStudents().add(student);
        } else {
            course.getStudents().remove(student);
        }
        save(course);
    }

}
