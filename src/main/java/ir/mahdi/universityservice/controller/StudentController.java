package ir.mahdi.universityservice.controller;

import ir.mahdi.universityservice.domain.Student;
import ir.mahdi.universityservice.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@AllArgsConstructor
@Controller
public class StudentController {

    private final StudentService studentService;

    private RoleController roleController;

//    public List<Student> getStudentsByCourseId(long id) {
////        studentService.findStudentsBy
//    }


    @GetMapping("/signup-student")
    public String getSignup(@ModelAttribute("student") Student student) {
        return "signup-student";
    }

    @PostMapping("/signup-student")
    public String save(@ModelAttribute("student") @Valid Student student,
                       BindingResult result, Errors errors) {
        if (result.hasErrors())
            return "signup-student";
        if (studentService.findByUsername(student.getUsername()).isEmpty()) {
            student.getRoles().add(roleController.findByName("student"));
            studentService.save(student);
            return "signup-successfully";
        }
        return "signup-student";
    }

}
