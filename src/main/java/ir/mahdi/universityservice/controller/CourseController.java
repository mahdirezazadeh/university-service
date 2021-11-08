package ir.mahdi.universityservice.controller;

import ir.mahdi.universityservice.domain.Course;
import ir.mahdi.universityservice.service.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@AllArgsConstructor
@RestController
public class CourseController {

    private CourseService courseService;

    @GetMapping("/create-course")
    public String createCourse(Course course) {
        return "create-course";
    }

    @PostMapping("/create-course")
    public String addCourse(@ModelAttribute("course") @Valid Course course,
                            HttpServletRequest request, Errors errors) {
        courseService.save(course);
        return "create-course";
    }
}
