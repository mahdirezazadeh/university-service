package ir.mahdi.universityservice.controller;

import ir.mahdi.universityservice.domain.Teacher;
import ir.mahdi.universityservice.service.TeacherService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@AllArgsConstructor
@Controller
public class TeacherController {

    private final TeacherService teacherService;

    private final RoleController roleController;

    @GetMapping("/signup-teacher")
    public String getSignup(Teacher teacher) {
        return "signup-teacher";
    }

    @PostMapping("/signup-teacher")
    public String save(@Valid Teacher teacher, BindingResult result, Model model) {
        if (result.hasErrors())
            return "signup-teacher";
        if (teacherService.findByUsername(teacher.getUsername()).isEmpty()) {
            teacher.getRoles().add(roleController.findByName("teacher"));
            teacherService.save(teacher);
            return "signup-successfully";
        }
        return "signup-teacher";
    }

}
