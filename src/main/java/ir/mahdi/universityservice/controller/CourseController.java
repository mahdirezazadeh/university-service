package ir.mahdi.universityservice.controller;

import ir.mahdi.universityservice.domain.Course;
import ir.mahdi.universityservice.domain.Exam;
import ir.mahdi.universityservice.domain.Student;
import ir.mahdi.universityservice.domain.Teacher;
import ir.mahdi.universityservice.mapper.CourseMapperToCourseDTO;
import ir.mahdi.universityservice.service.CourseService;
import ir.mahdi.universityservice.service.StudentService;
import ir.mahdi.universityservice.service.TeacherService;
import ir.mahdi.universityservice.service.dto.CourseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor
@Controller
@RequestMapping("/course")
public class CourseController {

    private final CourseService courseService;
    private final CourseMapperToCourseDTO mapperToCourseDTO;
    private final TeacherService teacherService;
    private final StudentService studentService;

    /**
     * a method for getting course creation page
     *
     * @param course a course dto passing to page
     * @return course creation page
     */

    @PreAuthorize("hasRole('admin')")
    @GetMapping("/create")
    public String createCourse(@ModelAttribute("course") CourseDTO course) {
        return "create-course";
    }

    /**
     * a method for creating course
     *
     * @param courseDTO gets courseDTO as DTO of course that has data of just created course
     * @param result    getting result for detecting errors if exist
     * @param model     a model for adding attributes
     * @return returns to course list page
     */
    @PreAuthorize("hasRole('admin')")
    @PostMapping("/create")
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

    /**
     * a method for getting courses list, authorized by admin
     *
     * @param model a model for adding attributes
     * @return page for listing courses
     */
    @PreAuthorize("hasRole('admin')")
    @GetMapping("/list")
    public String courseList(Model model) {
        model.addAttribute("courses", courseService.findAll());
        return "course-list";
    }

    /**
     * a method for getting course by id authorized by just admin
     *
     * @param id    id of course
     * @param model a model for adding attributes
     * @return page of course for admins
     */
    @PreAuthorize("hasRole('admin')")
    @GetMapping("")
    public String getCourseById(@RequestParam long id, Model model) {
        Optional<Course> course = courseService.findById(id);
        try {
            model.addAttribute("course", course.get());
//            model.addAttribute("courseStudents", StudentController.getStudentsByCourseId(id));
//            model.addAttribute("notInvolvedStudents", StudentController.getStudentsByNotCourseId(id));
            return "admin-course";
        } catch (Exception e) {
            model.addAttribute("message", "Course does not exist!");
            return "error-page";
        }
    }

    /**
     * a method to edit teacher of course authorized by admin
     *
     * @param courseId an id of course
     * @param model    a model for adding attributes
     * @return return page for editing course teacher
     */
    @PreAuthorize("hasRole('admin')")
    @GetMapping("/edit-teacher")
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

    /**
     * a method to edit students of course authorized by admin
     *
     * @param courseId an id of course
     * @param model    a model for adding attributes
     * @return return page for editing course teacher
     */
    @PreAuthorize("hasRole('admin')")
    @GetMapping("/edit-students")
    public String editStudents(@RequestParam long courseId, Model model) {
        Optional<Course> course = courseService.findById(courseId);
        if (course.isPresent()) {
            model.addAttribute("courseId", course.get().getId());
            Set<Student> studentsInCourse = course.get().getStudents();
            model.addAttribute("studentsInCourse", studentsInCourse);
            List<Student> studentsOutCourse = studentService.findOtherStudents(studentsInCourse);
            model.addAttribute("studentsOutCourse", studentsOutCourse);
            return "edit-course-students";
        }
        model.addAttribute("message", "Course does not exist!");
        return "error-page";
    }

    /**
     * Add exam to course by course id
     *
     * @param courseId a long value that points to course id
     * @param model    a model for adding courseId as attribute
     * @param exam     and exam for passing to page
     * @return return exam adding page that contains a form to get exam info
     */
    @PreAuthorize("hasRole('teacher')")
    @GetMapping("/add-exam")
    public String addExam(@RequestParam long courseId, Model model, Exam exam) {
        model.addAttribute("courseId", courseId);
        return "add-exam";
    }


}
