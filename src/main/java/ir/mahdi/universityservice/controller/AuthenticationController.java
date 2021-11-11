package ir.mahdi.universityservice.controller;

import ir.mahdi.universityservice.domain.base.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthenticationController {

    @GetMapping({"/", "/home"})
    public String homePage() {
        return "home";
    }


    @GetMapping("/login")
    public String loginGet(User user) {
        return "login";
    }


    @GetMapping("/sign-up")
    public String signUpGet(User user) {
        return "signup";
    }
}
