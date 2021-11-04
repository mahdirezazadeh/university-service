package ir.mahdi.universityservice.controller;

import ir.mahdi.universityservice.controller.mapper.UserToTeacher;
import ir.mahdi.universityservice.domain.Teacher;
import ir.mahdi.universityservice.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TeacherController {

    private final TeacherService teacherService;

    private final UserToTeacher userToTeacher;

    @Autowired
    public TeacherController(TeacherService teacherService, UserToTeacher userToTeacher) {
        this.teacherService = teacherService;
        this.userToTeacher = userToTeacher;
    }


    @GetMapping("/signup-teacher")
    public String getSignup(Teacher teacher) {
        return "signup-teacher";
    }

    @PostMapping("/signup-teacher")
    public String save(Teacher teacher, BindingResult result, Model model) {
        if (teacherService.findByUsername(teacher.getUsername()).isEmpty()) {
            teacherService.save(teacher);
            return "signup-successfully";
        }
        return "signup-teacher";
    }
}
