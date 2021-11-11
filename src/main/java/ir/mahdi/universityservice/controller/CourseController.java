package ir.mahdi.universityservice.controller;

import ir.mahdi.universityservice.domain.Course;
import ir.mahdi.universityservice.domain.Exam;
import ir.mahdi.universityservice.domain.Teacher;
import ir.mahdi.universityservice.mapper.CourseMapperToCourseDTO;
import ir.mahdi.universityservice.service.CourseService;
import ir.mahdi.universityservice.service.TeacherService;
import ir.mahdi.universityservice.service.dto.CourseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Controller
public class CourseController {

    private final CourseService courseService;
    private final CourseMapperToCourseDTO mapperToCourseDTO;
    private final TeacherService teacherService;

    //    TODO add create-corese operation to db and use it instead of admin role
    @PreAuthorize("hasRole('admin')")
    @GetMapping("/course/create")
    public String createCourse(@ModelAttribute("course") CourseDTO course) {
        return "create-course";
    }

    @PreAuthorize("hasRole('admin')")
    @PostMapping("/course/create")
    public String addCourse(@ModelAttribute("course") @Valid CourseDTO courseDTO,
                            BindingResult result, Model model) {
        if (result.hasErrors())
            return "create-course";
        try {
            Course course = mapperToCourseDTO.convertDTOToCourse(courseDTO);
            courseService.save(course);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return courseList(model);
    }

    @PreAuthorize("hasRole('admin')")
    @GetMapping("/course/list")
    public String courseList(Model model) {
        model.addAttribute("courses", courseService.findAll());
        return "course-list";
    }

    @PreAuthorize("hasRole('admin')")
    @GetMapping("/course")
    public String getCourseById(@RequestParam long id, Model model) {
        Optional<Course> course = courseService.findById(id);
        if (course.isPresent()) {
            model.addAttribute("course", course.get());
//            model.addAttribute("courseStudents", StudentController.getStudentsByCourseId(id));
//            model.addAttribute("notInvolvedStudents", StudentController.getStudentsByNotCourseId(id));
            return "admin-course";
        }
        model.addAttribute("message", "Course does not exist!");
        return "error-page";
    }

    @PreAuthorize("hasRole('admin')")
    @GetMapping("/course/edit-teacher")
    public String editTeacher(@RequestParam long courseId, Model model) {
        Optional<Course> course = courseService.findById(courseId);
        if (course.isPresent()) {
            List<Teacher> availableTeachers = teacherService.findAvailableTeachers();
            model.addAttribute("courseId", course.get().getId());
            try {
                model.addAttribute("currentTeacherId", course.get().getTeacher().getId());
            } catch (Exception e) {
                model.addAttribute("currentTeacherId", 0);
            }
            model.addAttribute("teachers", availableTeachers);
            return "edit-course-teacher";
        }
        model.addAttribute("message", "Course does not exist!");
        return "error-page";
    }

    @PreAuthorize("hasRole('teacher')")
    @GetMapping("/course/add-exam")
    public String addExam(@RequestParam long courseId, Model model, Exam exam) {
        model.addAttribute("courseId", courseId);
        return "add-exam";
    }


}
