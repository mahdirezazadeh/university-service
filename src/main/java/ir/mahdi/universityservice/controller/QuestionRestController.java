package ir.mahdi.universityservice.controller;

import ir.mahdi.universityservice.service.ExamQuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class QuestionRestController {

    private final ExamQuestionService examQuestionService;


    @PreAuthorize("hasRole('teacher')")
    @PostMapping("/question/remove-from-exam")
    public HttpStatus softDelete(@RequestParam long examQuestionId) {
        System.out.println(examQuestionId);
        examQuestionService.deleteById(examQuestionId);

        return HttpStatus.ACCEPTED;
    }


}
