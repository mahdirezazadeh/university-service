package ir.mahdi.universityservice.service.impl;

import ir.mahdi.universityservice.base.service.impl.BaseServiceImpl;
import ir.mahdi.universityservice.domain.Course;
import ir.mahdi.universityservice.domain.Exam;
import ir.mahdi.universityservice.domain.Role;
import ir.mahdi.universityservice.domain.Teacher;
import ir.mahdi.universityservice.exceptions.ItemAlreadyExist;
import ir.mahdi.universityservice.exceptions.ItemDoesNotExistException;
import ir.mahdi.universityservice.repository.TeacherRepository;
import ir.mahdi.universityservice.service.CourseService;
import ir.mahdi.universityservice.service.ExamService;
import ir.mahdi.universityservice.service.RoleService;
import ir.mahdi.universityservice.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;


@Service
@Transactional(readOnly = true)
public class TeacherServiceImpl extends BaseServiceImpl<Teacher, Long, TeacherRepository> implements TeacherService {

    @Autowired
    public TeacherServiceImpl(TeacherRepository repository) {
        super(repository);
    }

    @Autowired
    private CourseService courseService;
    @Autowired
    private ExamService examService;
    @Autowired
    private RoleService roleService;

    @Override
    @Transactional
    public Teacher save(Teacher teacher) {
        return super.save(teacher);
    }


    @Override
    @Transactional
    public List<Teacher> saveAll(Collection<Teacher> teachers) {
        return super.saveAll(teachers);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public Optional<Teacher> findByUsername(String username) {
        return repository.findTeacherByUsername(username);
    }

    @Override
    public List<Teacher> findAvailableTeachers() {
        return repository.findTeacherByActiveAndConfirmedAndDeleted(true, true, false);
    }

    @Override
    public List<Course> findCurrentUserTeacherCourses() {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<Teacher> teacher = findByUsername(username);
        List<Course> courses = findCoursesByTeacher(teacher.get());
        return courses;
    }

    @Override
    public Course loadCourseById(long id) {
        return courseService.findById(id).orElseThrow(() -> new ItemDoesNotExistException("Course"));
    }

    @Override
    public List<Exam> loadExamsByCourse(Course course) {
        return examService.findExamsByCourse(course);
    }

    @Override
    public void signUpTeacher(Teacher teacher) {
        if (findByUsername(teacher.getUsername()).isEmpty()) {
            Role teacherRole = findRoleByName("teacher");
            teacher.getRoles().add(teacherRole);
            save(teacher);
        } else {
            throw new ItemAlreadyExist("teacher with this username");
        }
    }

    private List<Course> findCoursesByTeacher(Teacher teacher) {
        return courseService.findCoursesByTeacher(teacher);
    }

    private Role findRoleByName(String roleName) {
        return roleService.findByName(roleName).orElseThrow(() -> new ItemDoesNotExistException("Role"));
    }
}
