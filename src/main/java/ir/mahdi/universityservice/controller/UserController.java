package ir.mahdi.universityservice.controller;

import ir.mahdi.universityservice.domain.base.User;
import ir.mahdi.universityservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasRole('admin')")
    @GetMapping("/users-list")
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    @PreAuthorize("hasAuthority('userConfirmation')")
    @PostMapping("/user/confirm")
    public boolean activateUserById(long id) {
        return userService.activateUserById(id);
    }

    @PreAuthorize("hasAuthority('userConfirmation')")
    @PostMapping("/user/disprove")
    public boolean deactivateUserById(long id) {
        return userService.deactivateUserById(id);
    }
}
