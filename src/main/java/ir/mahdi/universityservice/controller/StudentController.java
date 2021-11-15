package ir.mahdi.universityservice.controller;

import ir.mahdi.universityservice.domain.Student;
import ir.mahdi.universityservice.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@AllArgsConstructor
@Controller
public class StudentController {

    private final StudentService studentService;

    private RoleController roleController;

    /**
     * a method for getting signup students page
     *
     * @param student student object for passing to page
     * @return sign up page
     */
    @GetMapping("/signup-student")
    public String getSignup(@ModelAttribute("student") Student student) {
        return "signup-student";
    }

    /**
     * a method for sign up students by post method
     *
     * @param student student object that wants to sign up
     * @param result  mistakes and errors occurred by client
     * @return sign-up successfully page if sign up done otherwise returns to sign up pgae
     */
    @PostMapping("/signup-student")
    public String save(@ModelAttribute("student") @Valid Student student,
                       BindingResult result) {
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
