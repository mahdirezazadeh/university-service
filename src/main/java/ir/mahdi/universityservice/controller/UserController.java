package ir.mahdi.universityservice.controller;

import ir.mahdi.universityservice.domain.base.User;
import ir.mahdi.universityservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    /**
     * get list of all users
     *
     * @return list of all users
     */
    @PreAuthorize("hasRole('admin')")
    @GetMapping("/users-list")
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    /**
     * confirm users by id
     *
     * @param id id of user
     * @return accepted status if user confirmed otherwise return bad request
     */
    @PreAuthorize("hasAuthority('userConfirmation')")
    @PostMapping("/user/confirm")
    public HttpStatus activateUserById(long id) {
        if (userService.activateUserById(id)) {
            return HttpStatus.ACCEPTED;
        }
        return HttpStatus.BAD_REQUEST;
    }

    /**
     * disprove user by id
     *
     * @param id id of user
     * @return accepted status if user disproved otherwise return bad request
     */
    @PreAuthorize("hasAuthority('userConfirmation')")
    @PostMapping("/user/disprove")
    public HttpStatus deactivateUserById(long id) {
        if (userService.deactivateUserById(id)) {
            return HttpStatus.ACCEPTED;
        }
        return HttpStatus.BAD_REQUEST;
    }
}
