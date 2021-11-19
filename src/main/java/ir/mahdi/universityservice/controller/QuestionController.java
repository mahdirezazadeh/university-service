package ir.mahdi.universityservice.controller;

import ir.mahdi.universityservice.domain.DescriptiveQuestion;
import ir.mahdi.universityservice.domain.ExamQuestion;
import ir.mahdi.universityservice.domain.MultipleChoiceQuestion;
import ir.mahdi.universityservice.mapper.ExamMapper;
import ir.mahdi.universityservice.service.DescriptiveQuestionService;
import ir.mahdi.universityservice.service.ExamQuestionService;
import ir.mahdi.universityservice.service.QuestionService;
import ir.mahdi.universityservice.service.dto.ExamDTO;
import lombok.RequiredArgsConstructor;
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

    private final ExamQuestionService examQuestionService;

    private final ExamRestController examRestController;

    private final DescriptiveQuestionService descriptiveQuestionService;

    private final ExamMapper examMapper;

    @GetMapping("/exam/questions")
    public String getQuestionsByExamId(Long examId, Model model) {
        ExamDTO examById = examRestController.getExamById(examId);
//        List<Question> questions = examQuestionService.findExamQuestionsByExamId(examId);
        List<ExamQuestion> questions = examQuestionService.findAllByExamId(examId);
        model.addAttribute("questions", questions);
        model.addAttribute("exam", examById);
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
    public String getDescriptiveQuestionCreateFrom(@RequestParam long examId, DescriptiveQuestion question, Model model) {
        model.addAttribute("examId", examId);
        model.addAttribute("question", question);
        return "question-create-descriptive";
    }

    @PostMapping("/exam/create-question/descriptive")
    public String saveDescriptiveQuestion(@RequestParam long examId, @Valid DescriptiveQuestion question, @RequestParam int score, Model model, BindingResult result) {

        if (result.hasErrors())
            return "question-create-descriptive";
        question.setCourse(examRestController.getCourseByExamId(examId));

        DescriptiveQuestion descriptiveQuestion = descriptiveQuestionService.save(question);
//        descriptiveQuestion.setCourse();
        ExamQuestion examQuestion = new ExamQuestion(
                examMapper.convertDTOToEntity(examRestController.getExamById(examId)),
                descriptiveQuestion,
                score
        );
        examQuestionService.save(examQuestion);

        return getQuestionsByExamId(examId, model);
    }

    @GetMapping("/exam/question-bank/add-exam")
    public String getAddQuestionFromBankForm(@RequestParam long examId, @RequestParam long questionId, Model model) {
        model.addAttribute("examId", examId);
        model.addAttribute("questionId", questionId);

        return "add-question-from-bank";
    }

    @PostMapping("/exam/question-bank/add-exam")
    public String addQuestionFromBank(@RequestParam long examId, @RequestParam long questionId, @RequestParam int score, Model model) {
        examQuestionService.createQuestionByExamIdQuestionIdScore(examId, questionId, score);
        return getQuestionsByExamId(examId, model);
    }

}
