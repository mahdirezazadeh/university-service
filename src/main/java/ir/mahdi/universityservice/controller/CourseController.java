package ir.mahdi.universityservice.controller;

import ir.mahdi.universityservice.domain.Course;
import ir.mahdi.universityservice.mapper.CourseMapperToCourseDTO;
import ir.mahdi.universityservice.service.CourseService;
import ir.mahdi.universityservice.service.dto.CourseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.text.ParseException;

@RequiredArgsConstructor
@Controller
public class CourseController {

    private final CourseService courseService;
    private final CourseMapperToCourseDTO mapperToCourseDTO;

    @GetMapping("/course/create")
    public String createCourse(@ModelAttribute("course") CourseDTO course) {
        return "create-course";
    }

    @PostMapping("/course/create")
    public String addCourse(@ModelAttribute("course") @Valid CourseDTO courseDTO,
                            BindingResult result) {
        if (result.hasErrors())
            return "create-course";
        try {
            Course course = mapperToCourseDTO.convertDTOToCourse(courseDTO);
            courseService.save(course);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "course-list";
    }

//    public String courseList()
}
