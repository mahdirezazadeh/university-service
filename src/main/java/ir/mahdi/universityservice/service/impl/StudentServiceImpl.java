package ir.mahdi.universityservice.service.impl;

import ir.mahdi.universityservice.base.BaseEntity;
import ir.mahdi.universityservice.base.service.impl.BaseServiceImpl;
import ir.mahdi.universityservice.domain.Course;
import ir.mahdi.universityservice.domain.Exam;
import ir.mahdi.universityservice.domain.Role;
import ir.mahdi.universityservice.domain.Student;
import ir.mahdi.universityservice.exceptions.ItemAlreadyExist;
import ir.mahdi.universityservice.exceptions.ItemDoesNotExistException;
import ir.mahdi.universityservice.repository.StudentRepository;
import ir.mahdi.universityservice.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional(readOnly = true)
public class StudentServiceImpl extends BaseServiceImpl<Student, Long, StudentRepository> implements StudentService {

    @Autowired
    public StudentServiceImpl(StudentRepository repository) {
        super(repository);
    }

    @Autowired
    private RoleService roleService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private UserService userService;
    @Autowired
    private StudentExamAnswerService studentExamAnswerService;
    @Autowired
    private ExamService examService;

    @Override
    @Transactional
    public Student save(Student student) {
        return super.save(student);
    }


    @Override
    @Transactional
    public List<Student> saveAll(Collection<Student> students) {
        return super.saveAll(students);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public Student findByUsername(String username) {
        return repository.findStudentByUsername(username).orElseThrow(() -> new ItemDoesNotExistException("student with this username"));
    }

    @Override
    public List<Student> findOtherStudents(Collection<Student> studentsInCourse) {
        List<Long> studentsInCourseIds = studentsInCourse.stream().map(BaseEntity::getId).collect(Collectors.toList());
        if (studentsInCourse.size() == 0)
            studentsInCourseIds.add(0L);
        return repository.findStudentsByIdIsNotIn(studentsInCourseIds);
    }

    @Override
    public void signup(Student student) {
        findByUsername(student.getUsername());
        if (userService.isUsernameAlreadyTaken(student.getUsername()))
            throw new ItemAlreadyExist(String.format("student with [%s] username", student.getUsername()));
        Role studentRole = roleService.findByName("student").get();
        student.getRoles().add(studentRole);
        save(student);
    }

    @Override
    public List<Course> findCoursesByCurrentStudent() {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Student student = findByUsername(username);
        List<Course> courses = courseService.findCoursesByStudent(student);
        return courses;
    }

    @Override
    public Course findCourseById(long id) {
        return courseService.findById(id).orElseThrow(() -> new ItemDoesNotExistException("course"));
    }

    @Override
    public List<Exam> findCurrentStudentUndoneExamsForCourse(Course course) {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Student student = findByUsername(username);
        List<Exam> doneExams = studentExamAnswerService.findExamsByStudentAndCourse(student, course);
        List<Exam> undoneExams = examService.findAllByCourseAndNotExams(course, doneExams);
        return undoneExams;
    }

}
