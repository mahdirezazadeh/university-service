package ir.mahdi.universityservice.controller;

import ir.mahdi.universityservice.domain.Exam;
import ir.mahdi.universityservice.domain.base.Question;
import ir.mahdi.universityservice.service.ExamService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ExamController {

    private final ExamService examService;

    private final TeacherController teacherController;

    private final CourseRestController courseRestController;
    private final QuestionRestController questionRestController;

    /**
     * creates exam for course, authorized for teachers
     *
     * @param exam     the exam object wants to add to course
     * @param courseId id of course for exam
     * @param model    model for adding attributes
     * @return returns to course page
     */
    @PreAuthorize("hasRole('teacher')")
    @PostMapping("/exam/create")
    public String createExam(@ModelAttribute("exam") @Valid Exam exam, @RequestParam long courseId, Model model) {
        exam.setCourse(courseRestController.getCourseById(courseId).get());
        examService.save(exam);
        return teacherController.getCourseById(courseId, model);
    }

    /**
     * for getting edit page of exam by id, authorized for teachers
     *
     * @param id        id of exam
     * @param model     a model for adding attributes
     * @param examAfter an exam obj for passing to page
     * @return exam edit page
     */
    @PreAuthorize("hasRole('teacher')")
    @GetMapping("/exam")
    public String createExam(@RequestParam long id, Model model, @ModelAttribute("examAfter") @Valid Exam examAfter) {
        model.addAttribute("exam", examService.findById(id).get());
        return "exam-edit-page";
    }

    /**
     * for editing exam by exam id, authorized for teachers
     *
     * @param examAfter
     * @param examId
     * @param model
     * @return
     */
    @PreAuthorize("hasRole('teacher')")
    @PostMapping("/exam/edit")
    public String editExam(@ModelAttribute("examAfter") @Valid Exam examAfter, @RequestParam long examId, Model model) {
        examAfter = examService.edit(examId, examAfter);
        return teacherController.getCourseById(examAfter.getCourse().getId(), model);
    }

    @PreAuthorize("hasRole('teacher')")
    @GetMapping("/exam/question-bank")
    public String getQuestionBankByExamId(@RequestParam long examId, Model model) {
        List<Question<?, ?>> bank = questionRestController.getQuestionBankByExamId(examService.findById(examId).get().getCourse());

        model.addAttribute("examId", examId);
        model.addAttribute("bank", bank);

        return "question-bank";
    }

//    @PreAuthorize("hasRole('teacher')")
//    @GetMapping("/exam/add-question")
//    public String addQuestionToExamById(@RequestParam long examId) {
//        return "create-question";
//    }
//
//    //    Its Not Acceptable
//    @PreAuthorize("hasRole('teacher')")
//    @PostMapping("/exam/add-question")
//    public String createQuestionForExam(@ModelAttribute("question") Question question, @RequestParam long examId, @RequestParam int score, Model model) {
//        ExamQuestion examQuestion = examService.addQuestionByExamId(examId, question, score);
//        return teacherController.getCourseById(examQuestion.getExam().getCourse().getId(), model);
//    }

}
