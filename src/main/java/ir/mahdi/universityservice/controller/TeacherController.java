package ir.mahdi.universityservice.controller;

import ir.mahdi.universityservice.domain.Course;
import ir.mahdi.universityservice.domain.Teacher;
import ir.mahdi.universityservice.service.TeacherService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Controller
public class TeacherController {

    private final TeacherService teacherService;

    private final RoleController roleController;

    private final CourseRestController courseRestController;

    private final ExamRestController examRestController;

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
        if (teacherService.findByUsername(teacher.getUsername()).isEmpty()) {
            teacher.getRoles().add(roleController.findByName("teacher"));
            teacherService.save(teacher);
            return "signup-successfully";
        }
        return "signup-teacher";
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
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<Teacher> teacher = teacherService.findByUsername(username);
        List<Course> courses = courseRestController.getCoursesByTeacher(teacher.get());
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
        Optional<Course> course = courseRestController.getCourseById(id);
        model.addAttribute("course", course.get());
        model.addAttribute("exams", examRestController.getExamsByCourse(course.get()));
        return "teacher-course";
    }


}
