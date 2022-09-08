package ir.mahdi.universityservice.controller;

import ir.mahdi.universityservice.domain.Course;
import ir.mahdi.universityservice.domain.Exam;
import ir.mahdi.universityservice.domain.Teacher;
import ir.mahdi.universityservice.service.TeacherService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@Controller
public class TeacherController {

    private final TeacherService teacherService;

    /**
     * for getting teacher sign up page
     *
     * @param teacher for passing teacher to sign up form
     * @return sign up page
     */
    @GetMapping("/signup-teacher")
    public String getSignup(Teacher teacher) {
        return "signup-teacher";
    }

    /**
     * for sign up teacher and save him to database
     *
     * @param teacher the teacher object
     * @param result  the result contains errors of client
     * @return sign-up successfully page if sign up done otherwise returns to sign up pgae
     */
    @PostMapping("/signup-teacher")
    public String save(@Valid Teacher teacher, BindingResult result) {
        if (result.hasErrors())
            return "signup-teacher";
        try {
            teacherService.signUpTeacher(teacher);
            return "signup-successfully";
        } catch (Exception ex) {
            return "signup-teacher";
        }
    }

    /**
     * gets list of courses for teacher
     *
     * @param model a model for adding attributes
     * @return teacher courses list page
     */
    @PreAuthorize("hasRole('teacher')")
    @GetMapping("/teacher/course/list")
    public String getCoursesByTeacher(Model model) {
        List<Course> courses = teacherService.findCurrentUserTeacherCourses();
        model.addAttribute("courses", courses);
        return "teacher-courses";
    }

    /**
     * get course page by id
     *
     * @param id    id ov course
     * @param model a model for adding attributes
     * @return return course page
     */
    @PreAuthorize("hasRole('teacher')")
    @GetMapping("/teacher/course")
    public String getCourseById(@RequestParam long id, Model model) {
        Course course = teacherService.loadCourseById(id);
        List<Exam> exams = teacherService.loadExamsByCourse(course);
        model.addAttribute("course", course);
        model.addAttribute("exams", exams);
        return "teacher-course";
    }


}
