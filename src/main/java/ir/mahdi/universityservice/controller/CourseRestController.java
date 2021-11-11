package ir.mahdi.universityservice.controller;

import ir.mahdi.universityservice.domain.Course;
import ir.mahdi.universityservice.domain.Teacher;
import ir.mahdi.universityservice.service.CourseService;
import ir.mahdi.universityservice.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class CourseRestController {

    private final CourseService courseService;
    private final TeacherService teacherService;

    @PreAuthorize("hasRole('admin')")
    @PostMapping("/course/set-teacher")
    public HttpStatus setTeacher(long teacherId, long courseId) {
        Optional<Course> course = courseService.findById(courseId);
        Optional<Teacher> teacher = teacherService.findById(teacherId);
        if (course.isPresent() && teacher.isPresent()) {
            Course course1 = course.get();
            course1.setTeacher(teacher.get());
            courseService.save(course1);
            return HttpStatus.ACCEPTED;
        }
        return HttpStatus.BAD_REQUEST;
    }
}

