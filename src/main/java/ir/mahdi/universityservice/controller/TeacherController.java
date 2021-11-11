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

    @GetMapping("/signup-teacher")
    public String getSignup(Teacher teacher) {
        return "signup-teacher";
    }

    @PostMapping("/signup-teacher")
    public String save(@Valid Teacher teacher, BindingResult result, Model model) {
        if (result.hasErrors())
            return "signup-teacher";
        if (teacherService.findByUsername(teacher.getUsername()).isEmpty()) {
            teacher.getRoles().add(roleController.findByName("teacher"));
            teacherService.save(teacher);
            return "signup-successfully";
        }
        return "signup-teacher";
    }

    @PreAuthorize("hasRole('teacher')")
    @GetMapping("/teacher/course/list")
    public String getCoursesByTeacher(Model model) {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<Teacher> teacher = teacherService.findByUsername(username);
        List<Course> courses = courseRestController.getCoursesByTeacher(teacher.get());
        model.addAttribute("courses", courses);
        return "teacher-courses";
    }

    @PreAuthorize("hasRole('teacher')")
    @GetMapping("/teacher/course")
    public String getCourseById(@RequestParam long id, Model model) {
        Optional<Course> course = courseRestController.getCourseById(id);
        model.addAttribute("course", course.get());
        model.addAttribute("exams", examRestController.getExamsByCourse(course.get()));
        return "teacher-course";
    }


}
