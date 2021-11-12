package ir.mahdi.universityservice.controller;

import ir.mahdi.universityservice.domain.Exam;
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

@Controller
@RequiredArgsConstructor
public class ExamController {

    private final ExamService examService;

    private final TeacherController teacherController;

    private final CourseRestController courseRestController;

    @PreAuthorize("hasRole('teacher')")
    @PostMapping("/exam/create")
    public String createExam(@ModelAttribute("exam") @Valid Exam exam, @RequestParam long courseId, Model model) {
        exam.setCourse(courseRestController.getCourseById(courseId).get());
        examService.save(exam);
        return teacherController.getCourseById(courseId, model);
    }

    //    TAHA
    @PreAuthorize("hasRole('teacher')")
    @GetMapping("/exam")
    public String createExam(@RequestParam long id, Model model, @ModelAttribute("examAfter") @Valid Exam examAfter) {
        model.addAttribute("exam", examService.findById(id).get());
        return "exam-edit-page";
    }

    @PreAuthorize("hasRole('teacher')")
    @PostMapping("/exam/edit")
    public String editExam(@ModelAttribute("examAfter") @Valid Exam examAfter, @RequestParam long examId, Model model) {
        examAfter = examService.edit(examId, examAfter);
        return teacherController.getCourseById(examAfter.getCourse().getId(), model);
    }

}
