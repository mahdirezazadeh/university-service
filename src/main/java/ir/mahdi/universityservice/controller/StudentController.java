package ir.mahdi.universityservice.controller;

import ir.mahdi.universityservice.controller.mapper.UserToStudent;
import ir.mahdi.universityservice.domain.Student;
import ir.mahdi.universityservice.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class StudentController {

    private final StudentService studentService;

    private final UserToStudent userToStudent;

    @Autowired
    public StudentController(StudentService studentService, UserToStudent userToStudent) {
        this.studentService = studentService;
        this.userToStudent = userToStudent;
    }

    @GetMapping("/signup-student")
    public String getSignup(Student student) {
        return "signup-student";
    }

    @PostMapping("/signup-student")
    public String save(Student student, BindingResult result, Model model) {
        if (studentService.findByUsername(student.getUsername()).isEmpty()) {
            studentService.save(student);
            return "signup-successfully";
        }
        return "signup-student";
    }


}
