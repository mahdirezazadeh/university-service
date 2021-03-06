package ir.mahdi.universityservice.controller;

import ir.mahdi.universityservice.domain.Course;
import ir.mahdi.universityservice.domain.base.Question;
import ir.mahdi.universityservice.service.ExamQuestionService;
import ir.mahdi.universityservice.service.QuestionService;
import ir.mahdi.universityservice.service.StudentExamAnswerService;
import ir.mahdi.universityservice.service.StudentQuestionAnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    private final StudentExamAnswerService examAnswerService;

    private final StudentQuestionAnswerService questionAnswerService;


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


    @PreAuthorize("hasRole('student')")
    @PostMapping("/question/save-answer")
    public HttpStatus setQuestionAnswer(@RequestParam long studentExamAnswerId, @RequestParam long questionId, @RequestParam String studentAnswer) {
        try {
            questionAnswerService.saveAnswer(studentExamAnswerId, questionId, studentAnswer);
            return HttpStatus.ACCEPTED;
        } catch (RuntimeException e) {
            return HttpStatus.FORBIDDEN;
        } catch (Exception e) {
            return HttpStatus.BAD_REQUEST;
        }

    }

    @PreAuthorize("hasRole('student')")
    @PostMapping("/question/save-answers")
    public HttpStatus setQuestionAnswers(@RequestParam long studentExamAnswerId, @RequestParam String[][] sendingAnswers) {
        System.out.println(studentExamAnswerId);
        for (String[] answer : sendingAnswers) {
            if (answer != null)
                setQuestionAnswer(studentExamAnswerId, Long.parseLong(answer[0]), answer[1]);
        }
        return HttpStatus.ACCEPTED;
    }


    @PreAuthorize("hasRole('teacher')")
    @PostMapping("/question/save-score")
    public ResponseEntity<? extends Object> setAnswerScore(@RequestParam long questionId, @RequestParam float teacherGivenScore) {
        try {
            questionAnswerService.saveAnswerScore(questionId, teacherGivenScore);
            return new ResponseEntity<Object>("score saved", HttpStatus.ACCEPTED);
        } catch (RuntimeException e) {
            return new ResponseEntity<Error>(new Error(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<Error>(new Error(), HttpStatus.NOT_FOUND);
        }

    }
}
