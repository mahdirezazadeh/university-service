package ir.mahdi.universityservice.controller;

import ir.mahdi.universityservice.service.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@AllArgsConstructor
@Controller
public class AdminController {

    private final AdminService adminService;

    private final UserController userController;


    /**
     * a method for getting all users by admin to confirm registration of users
     *
     * @param model a model for adding attributes
     * @return returns list of users page
     */
    @GetMapping("/admin/confirm-users")
    public String getAllUsersToConfirm(Model model) {
        model.addAttribute("users", userController.getAllUsers());
        return "confirm-users";
    }
}
