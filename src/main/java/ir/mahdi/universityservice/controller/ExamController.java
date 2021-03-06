package ir.mahdi.universityservice.controller;

import ir.mahdi.universityservice.domain.Exam;
import ir.mahdi.universityservice.domain.StudentExamAnswer;
import ir.mahdi.universityservice.domain.base.Question;
import ir.mahdi.universityservice.mapper.StudentExamAnswerMapper;
import ir.mahdi.universityservice.mapper.StudentQuestionMapper;
import ir.mahdi.universityservice.service.ExamService;
import ir.mahdi.universityservice.service.StudentExamAnswerService;
import ir.mahdi.universityservice.service.dto.StudentExamAnswersDTO;
import ir.mahdi.universityservice.service.dto.StudentQuestionDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
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

    private final StudentExamAnswerService studentExamAnswerService;

    private final TeacherController teacherController;

    private final CourseRestController courseRestController;

    private final QuestionRestController questionRestController;

    private final StudentQuestionMapper studentQuestionMapper;

    private final StudentExamAnswerMapper studentExamAnswerMapper;

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

    @PreAuthorize("hasRole('student')")
    @GetMapping("/exam/start")
    public String startExamForStudent(@RequestParam long examId, Model model) {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        StudentExamAnswer studentExamAnswer;
        try {
            Exam exam;
            try {
                exam = examService.findById(examId).get();
            } catch (Exception e) {
                model.addAttribute("message", "Exam does not exist!");
                return "error-page";
            }
            studentExamAnswer = studentExamAnswerService.startExam(exam, username);
        } catch (Exception e) {
            String message = e.getMessage();
            model.addAttribute("message", "Exam have been done by student!");
            return "error-page";
        }

        List<StudentQuestionDTO> studentQuestionDTOS = studentQuestionMapper.convertListQuestionToDTO(studentExamAnswer.getStudentAnswers());

        model.addAttribute("studentExamAnswerId", studentExamAnswer.getId());
        model.addAttribute("questions", studentQuestionDTOS);
        model.addAttribute("endTime", studentExamAnswer.getEndTime());

        return "start-exam";
    }

    @PreAuthorize("hasRole('teacher')")
    @GetMapping("/exam-students-answers")
    public String correctStudentsAnswers(@RequestParam long examId, Model model) {
        List<StudentExamAnswer> studentExamAnswers = studentExamAnswerService.findByExamId(examId);
        List<StudentExamAnswersDTO> studentExamAnswersDTOS = studentExamAnswerMapper.convertListStudentExamAnswerToDTO(studentExamAnswers);
        model.addAttribute("studentExamAnswersDTOS", studentExamAnswersDTOS);
        return "exam-students-answers";
    }

    @PreAuthorize("hasRole('teacher')")
    @GetMapping("/examAnswer")
    public String getCorrectStudentsAnswersPage(@RequestParam long examAnswerId, Model model) {
        try {
            StudentExamAnswer studentExamAnswer = studentExamAnswerService.findById(examAnswerId).get();

            List<StudentQuestionDTO> studentQuestionDTOS =
                    studentQuestionMapper.
                            convertListQuestionToDTOForTeacher(studentExamAnswer.getStudentAnswers());

            float examScore = studentQuestionMapper.calculateExamScore(studentQuestionDTOS);

            studentExamAnswer.setExamScore(examScore);
            studentExamAnswerService.save(studentExamAnswer);


            model.addAttribute("questions", studentQuestionDTOS);
            model.addAttribute("studentExamAnswerId", examAnswerId);

            return "correct-exam";
        } catch (Exception e) {
            model.addAttribute("message", "exam answer does not exist!");
            return "error-page";
        }
    }

}
