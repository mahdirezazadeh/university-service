package ir.mahdi.universityservice.controller;

import ir.mahdi.universityservice.domain.Course;
import ir.mahdi.universityservice.domain.Teacher;
import ir.mahdi.universityservice.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class CourseRestController {

    private final CourseService courseService;

    /**
     * a method for setting teacher to course by teacher id and course id
     *
     * @param teacherId an id of teacher
     * @param courseId  an id of course
     * @return accepted http status if teacher changes otherwise returns Bad request http status
     */
    @PreAuthorize("hasRole('admin')")
    @PostMapping("/course/set-teacher")
    public HttpStatus setTeacher(long teacherId, long courseId) {
        try {
            courseService.setTeacherForCourse(teacherId, courseId);
            return HttpStatus.ACCEPTED;
        } catch (Exception e) {
            return HttpStatus.BAD_REQUEST;
        }
    }

    /**
     * for getting list of courses by teacher, authorized for teachers
     *
     * @param teacher the teacher object
     * @return list of courses for the teacher
     */
    @PreAuthorize("hasRole('teacher')")
    @GetMapping("/teacher/courseByTeacher")
    public List<Course> getCoursesByTeacher(Teacher teacher) {
        return courseService.findCoursesByTeacher(teacher);
    }

    /**
     * for getting course by id, authorized for teachers
     *
     * @param id id of course
     * @return return Optional Course object
     */
    @PreAuthorize("hasAnyRole('teacher', 'student')")
    @GetMapping("/teacher/courseById")
    public Optional<Course> getCourseById(long id) {
        return courseService.findById(id);
    }

    @PreAuthorize("hasRole('admin')")
    @PostMapping("/course/add-or-remove-student")
    public HttpStatus addOrRemoveStudent(long studentId, long courseId) {
        courseService.addOrRemoveStudent(studentId, courseId);
        return HttpStatus.ACCEPTED;
    }
}

