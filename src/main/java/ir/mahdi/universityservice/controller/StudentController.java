package ir.mahdi.universityservice.controller;

import ir.mahdi.universityservice.domain.Course;
import ir.mahdi.universityservice.domain.Exam;
import ir.mahdi.universityservice.domain.Student;
import ir.mahdi.universityservice.service.ExamService;
import ir.mahdi.universityservice.service.StudentExamAnswerService;
import ir.mahdi.universityservice.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Controller
public class StudentController {

    private final StudentService studentService;

    private RoleController roleController;

    private CourseRestController courseRestController;

    private ExamRestController examRestController;

    private StudentExamAnswerService studentExamAnswerService;

    private ExamService examService;

    /**
     * a method for getting signup students page
     *
     * @param student student object for passing to page
     * @return sign up page
     */
    @GetMapping("/signup-student")
    public String getSignup(@ModelAttribute("student") Student student) {
        return "signup-student";
    }

    /**
     * a method for sign up students by post method
     *
     * @param student student object that wants to sign up
     * @param result  mistakes and errors occurred by client
     * @return sign-up successfully page if sign up done otherwise returns to sign up pgae
     */
    @PostMapping("/signup-student")
    public String save(@ModelAttribute("student") @Valid Student student,
                       BindingResult result) {
        if (result.hasErrors())
            return "signup-student";
        if (studentService.findByUsername(student.getUsername()).isEmpty()) {
            student.getRoles().add(roleController.findByName("student"));
            studentService.save(student);
            return "signup-successfully";
        }
        return "signup-student";
    }

    /**
     * gets list of courses for student
     *
     * @param model a model for adding attributes
     * @return student courses list page
     */
    @PreAuthorize("hasRole('student')")
    @GetMapping("/student/course/list")
    public String getCoursesByTeacher(Model model) {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<Student> student = studentService.findByUsername(username);
        List<Course> courses = courseRestController.getCoursesByStudent(student.get());
        model.addAttribute("courses", courses);
        return "student-courses";
    }

    /**
     * get course page by id
     *
     * @param id    id ov course
     * @param model a model for adding attributes
     * @return return course page
     */
    @PreAuthorize("hasRole('student')")
    @GetMapping("/student/course")
    public String getCourseById(@RequestParam long id, Model model) {
//        find student
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<Student> student = studentService.findByUsername(username);

//        find course
        Optional<Course> course = courseRestController.getCourseById(id);

//        find done exams by students(unavailable exams) for this course
        List<Exam> doneExams = studentExamAnswerService.findExamsByStudentAndCourse(student.get(), course.get());

//        find undone exams by students(available exams) for this course
        List<Exam> undoneExams = examService.findAllByCourseAndNotExams(course.get(), doneExams);


        model.addAttribute("course", course.get());
        model.addAttribute("exams", undoneExams);
        return "student-course";
    }

}
