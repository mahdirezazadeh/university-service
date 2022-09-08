package ir.mahdi.universityservice.controller;

import ir.mahdi.universityservice.domain.base.User;
import ir.mahdi.universityservice.service.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@AllArgsConstructor
@Controller
public class AdminController {

    private final AdminService adminService;


    /**
     * a method for getting all users by admin to confirm registration of users
     *
     * @param model a model for adding attributes
     * @return returns list of users page
     */
    @GetMapping("/admin/confirm-users")
    public String getAllUsersToConfirm(Model model) {
        List<User> users = adminService.loadAllUsers();
        model.addAttribute("users", users);
        return "confirm-users";
    }
}
