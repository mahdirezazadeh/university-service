package ir.mahdi.universityservice.controller;

import ir.mahdi.universityservice.domain.Course;
import ir.mahdi.universityservice.domain.base.Question;
import ir.mahdi.universityservice.service.ExamQuestionService;
import ir.mahdi.universityservice.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class QuestionRestController {

    private final ExamQuestionService examQuestionService;

    private final QuestionService questionService;


    @PreAuthorize("hasRole('teacher')")
    @PostMapping("/question/remove-from-exam")
    public HttpStatus softDelete(@RequestParam long examQuestionId) {
        System.out.println(examQuestionId);
        examQuestionService.deleteById(examQuestionId);

        return HttpStatus.ACCEPTED;
    }

    @PreAuthorize("hasRole('teacher')")
    @PostMapping("/question/delete")
    public HttpStatus delete(@RequestParam long questionId) {
        questionService.deleteById(questionId);
        return HttpStatus.ACCEPTED;
    }


    @PreAuthorize("hasRole('teacher')")
    public List<Question<?, ?>> getQuestionBankByExamId(Course course) {
        return questionService.findQuestionsByExam(course);
    }
}
