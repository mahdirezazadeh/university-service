package ir.mahdi.universityservice.controller;

import ir.mahdi.universityservice.domain.DescriptiveQuestion;
import ir.mahdi.universityservice.domain.MultipleChoiceQuestion;
import ir.mahdi.universityservice.domain.base.Question;
import ir.mahdi.universityservice.service.ExamQuestionService;
import ir.mahdi.universityservice.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;

//    private final QuestionMapper questionMapper;

    private final ExamQuestionService examQuestionService;

    private final ExamRestController examRestController;

    @GetMapping("/exam/questions")
    public String getQuestionsByExamId(Long examId, Model model) {
        examRestController.getExamById(examId, model);
        List<Question> questions = examQuestionService.findExamQuestionsByExamId(examId);
        model.addAttribute("questions", questions);
        return "exam-questions";
    }

    @GetMapping("/exam/question-type")
    public String getQuestionTypePage(@RequestParam long examId, Model model) {
        model.addAttribute("examId", examId);
        return "question-types";
    }

    @GetMapping("/exam/create-question/multi-answer")
    public String getMultiAnswerQuestionCreateFrom(@RequestParam long examId, MultipleChoiceQuestion question) {
        return "question-create-multi-choice";
    }


    @GetMapping("/exam/create-question/descriptive")
    public String getDescriptiveQuestionCreateFrom(@RequestParam long examId, DescriptiveQuestion question) {
        return "question-create-descriptive";
    }

}
