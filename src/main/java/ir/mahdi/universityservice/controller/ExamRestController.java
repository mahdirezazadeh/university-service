package ir.mahdi.universityservice.controller;

import ir.mahdi.universityservice.domain.Course;
import ir.mahdi.universityservice.domain.Exam;
import ir.mahdi.universityservice.service.ExamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ExamRestController {

    private final ExamService examService;

    @PreAuthorize("hasRole('teacher')")
    @GetMapping("/ExamByCourse")
    public List<Exam> getExamsByCourse(Course course) {
        return examService.findExamsByCourse(course);
    }

    @PreAuthorize("hasRole('teacher')")
    @PostMapping("/exam/delete")
    public HttpStatus softDelete(long examId) {
        if (examService.softDelete(examId))
            return HttpStatus.ACCEPTED;
        return HttpStatus.BAD_REQUEST;
    }

}
