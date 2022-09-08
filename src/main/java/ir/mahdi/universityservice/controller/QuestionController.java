package ir.mahdi.universityservice.controller;

import ir.mahdi.universityservice.domain.DescriptiveQuestion;
import ir.mahdi.universityservice.domain.Exam;
import ir.mahdi.universityservice.domain.ExamQuestion;
import ir.mahdi.universityservice.domain.MultipleChoiceQuestion;
import ir.mahdi.universityservice.mapper.ExamMapper;
import ir.mahdi.universityservice.service.QuestionService;
import ir.mahdi.universityservice.service.dto.ExamDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;

//    private final QuestionMapper questionMapper;

    private final ExamMapper examMapper;

    @PreAuthorize("hasRole('teacher')")
    @GetMapping("/exam/questions")
    public String getQuestionsByExamId(Long examId, Model model) {
        Exam exam = questionService.getExamById(examId);
        ExamDTO examDTO = examMapper.convertEntityToDTO(exam);
        List<ExamQuestion> questions = questionService.findAllExamQuestionByExamId(examId);
        float examScore = sumOfScores(questions);
//        List<Question> questions = examQuestionService.findExamQuestionsByExamId(examId);
        model.addAttribute("exam", examDTO);
        model.addAttribute("questions", questions);
        model.addAttribute("examScore", examScore);
        return "exam-questions";
    }

    private float sumOfScores(List<ExamQuestion> questions) {
        float score = 0;
        for (ExamQuestion question : questions
        ) {
            score += question.getScore();
        }
        return score;
    }

    @PreAuthorize("hasRole('teacher')")
    @GetMapping("/exam/question-type")
    public String getQuestionTypePage(@RequestParam long examId, Model model) {
        model.addAttribute("examId", examId);
        return "question-types";
    }

    @PreAuthorize("hasRole('teacher')")
    @GetMapping("/exam/create-question/multi-answer")
    public String getMultiAnswerQuestionCreateFrom(@RequestParam long examId, MultipleChoiceQuestion question, Model model) {
        List<String> choices = question.getChoices();
//        choices.add("");
//        choices.add(" ");
        model.addAttribute("question", question);
        model.addAttribute("choices", choices);
        model.addAttribute("examId", examId);
        return "question-create-multi-choice";
    }

    @PreAuthorize("hasRole('teacher')")
    @PostMapping("/exam/create-question/multi-answer")
    public String saveMultiAnswerQuestion(@RequestParam long examId,
                                          @Valid MultipleChoiceQuestion question,
                                          @RequestParam float score,
                                          @RequestParam String rightAnswer,
                                          Model model,
                                          BindingResult result) {

        if (result.hasErrors())
            return "question-create-multi-choice";

        questionService.saveMultiAnswerQuestion(question, rightAnswer, score, examId);

        return getQuestionsByExamId(examId, model);
    }


    @PreAuthorize("hasRole('teacher')")
    @GetMapping("/exam/create-question/descriptive")
    public String getDescriptiveQuestionCreateFrom(@RequestParam long examId, DescriptiveQuestion question, Model model) {

        model.addAttribute("examId", examId);
        model.addAttribute("question", question);
        return "question-create-descriptive";
    }

    @PreAuthorize("hasRole('teacher')")
    @PostMapping("/exam/create-question/descriptive")
    public String saveDescriptiveQuestion(@RequestParam long examId,
                                          @Valid DescriptiveQuestion question,
                                          @RequestParam float score,
                                          Model model,
                                          BindingResult result) {

        if (result.hasErrors())
            return "question-create-descriptive";

        questionService.saveDescriptiveQuestion(question, score, examId);

        return getQuestionsByExamId(examId, model);
    }

    @PreAuthorize("hasRole('teacher')")
    @GetMapping("/exam/question-bank/add-exam")
    public String getAddQuestionFromBankForm(@RequestParam long examId, @RequestParam long questionId, Model model) {
        model.addAttribute("examId", examId);
        model.addAttribute("questionId", questionId);
        return "add-question-from-bank";
    }

    @PreAuthorize("hasRole('teacher')")
    @PostMapping("/exam/question-bank/add-exam")
    public String addQuestionFromBank(@RequestParam long examId, @RequestParam long questionId, @RequestParam int score, Model model) {
        try {
            questionService.createQuestionByExamIdQuestionIdScore(examId, questionId, score);
            return getQuestionsByExamId(examId, model);
        } catch (Exception e) {
            model.addAttribute("message", "question have been added to exam before!");
            return "error-page";
        }
    }

}
