package ir.mahdi.universityservice.controller;

import ir.mahdi.universityservice.domain.base.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthenticationController {

    /**
     * a method for getting home page
     *
     * @return
     */
    @GetMapping({"/", "/home"})
    public String homePage() {
        return "home";
    }


    /**
     * a method for getting login page
     *
     * @param user user object for passing to page
     * @return login page
     */
    @GetMapping("/login")
    public String loginGet(User user) {
        return "login";
    }


    /**
     * a method fot getting signup page
     *
     * @param user user object for passing to page
     * @return signup page
     */
    @GetMapping("/sign-up")
    public String signUpGet(User user) {
        return "signup";
    }
}
