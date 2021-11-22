package ir.mahdi.universityservice.controller;

import ir.mahdi.universityservice.domain.Role;
import ir.mahdi.universityservice.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class RoleController {

    private RoleService roleService;

    /**
     * for getting role object by name
     *
     * @param name name of role
     * @return role object
     */
    public Role findByName(String name) {
        return roleService.findByName(name).get();
    }
}
