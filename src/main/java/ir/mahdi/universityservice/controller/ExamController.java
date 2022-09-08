package ir.mahdi.universityservice.controller;

import ir.mahdi.universityservice.domain.Exam;
import ir.mahdi.universityservice.domain.StudentExamAnswer;
import ir.mahdi.universityservice.domain.base.Question;
import ir.mahdi.universityservice.exceptions.ItemDoesNotExistException;
import ir.mahdi.universityservice.mapper.StudentExamAnswerMapper;
import ir.mahdi.universityservice.mapper.StudentQuestionMapper;
import ir.mahdi.universityservice.service.ExamService;
import ir.mahdi.universityservice.service.dto.StudentExamAnswersDTO;
import ir.mahdi.universityservice.service.dto.StudentQuestionDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ExamController {

    private final ExamService examService;

    private final StudentQuestionMapper studentQuestionMapper;

    private final StudentExamAnswerMapper studentExamAnswerMapper;

    /**
     * creates exam for course, authorized for teachers
     *
     * @param exam     the exam object wants to add to course
     * @param courseId id of course for exam
     */
    @PreAuthorize("hasRole('teacher')")
    @PostMapping("/exam/create")
    public void createExam(HttpServletResponse response, @ModelAttribute("exam") @Valid Exam exam, @RequestParam long courseId) {
        exam.setCourse(examService.getCourseById(courseId));
        examService.save(exam);
        try {
            response.sendRedirect("/teacher/course?id=" + courseId);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//        return teacherController.getCourseById(courseId, model);
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
        Exam exam = examService.findById(id).orElseThrow(() -> new ItemDoesNotExistException("Exam"));
        model.addAttribute("exam", exam);
        return "exam-edit-page";
    }

    /**
     * for editing exam by exam id, authorized for teachers
     *
     * @param examAfter
     * @param examId
     * @param response
     */
    @PreAuthorize("hasRole('teacher')")
    @PostMapping("/exam/edit")
    public void editExam(@ModelAttribute("examAfter") @Valid Exam examAfter, @RequestParam long examId, HttpServletResponse response) {
        examAfter = examService.edit(examId, examAfter);

        try {
            response.sendRedirect("/teacher/course?id=" + examAfter.getCourse().getId());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//        return teacherController.getCourseById(examAfter.getCourse().getId(), model);
    }

    @PreAuthorize("hasRole('teacher')")
    @GetMapping("/exam/question-bank")
    public String getQuestionBankByExamId(@RequestParam long examId, Model model) {
        List<Question<?, ?>> bank = examService.getQuestionBankByExamId(examId);

        model.addAttribute("examId", examId);
        model.addAttribute("bank", bank);
        return "question-bank";
    }

    @PreAuthorize("hasRole('student')")
    @GetMapping("/exam/start")
    public String startExamForStudent(@RequestParam long examId, Model model) {
        try {
            Exam exam = examService.findById(examId).orElseThrow(() -> new ItemDoesNotExistException("Exam"));
            StudentExamAnswer studentExamAnswer = examService.startExamForCurrentUser(exam);
            List<StudentQuestionDTO> studentQuestionDTOS = studentQuestionMapper.convertListQuestionToDTO(studentExamAnswer.getStudentAnswers());

            model.addAttribute("studentExamAnswerId", studentExamAnswer.getId());
            model.addAttribute("questions", studentQuestionDTOS);
            model.addAttribute("endTime", studentExamAnswer.getEndTime());
            return "start-exam";
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
            return "error-page";
        }
    }

    @PreAuthorize("hasRole('teacher')")
    @GetMapping("/exam-students-answers")
    public String correctStudentsAnswers(@RequestParam long examId, Model model) {
        List<StudentExamAnswer> studentExamAnswers = examService.findStudentExamAnswerByExamId(examId);
        List<StudentExamAnswersDTO> studentExamAnswersDTOS = studentExamAnswerMapper.convertListStudentExamAnswerToDTO(studentExamAnswers);
        model.addAttribute("studentExamAnswersDTOS", studentExamAnswersDTOS);
        return "exam-students-answers";
    }

    @PreAuthorize("hasRole('teacher')")
    @GetMapping("/examAnswer")
    public String getCorrectStudentsAnswersPage(@RequestParam long examAnswerId, Model model) {
        try {
            StudentExamAnswer studentExamAnswer = examService.findStudentExamAnswerById(examAnswerId);
            List<StudentQuestionDTO> studentQuestionDTOS =
                    studentQuestionMapper.
                            convertListQuestionToDTOForTeacher(studentExamAnswer.getStudentAnswers());
            float examScore = studentQuestionMapper.calculateExamScore(studentQuestionDTOS);

            studentExamAnswer.setExamScore(examScore);
            examService.saveStudentExamAnswer(studentExamAnswer);
            model.addAttribute("questions", studentQuestionDTOS);
            model.addAttribute("studentExamAnswerId", examAnswerId);
            return "correct-exam";
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
            return "error-page";
        }
    }

}
