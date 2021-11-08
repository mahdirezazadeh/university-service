package ir.mahdi.universityservice.service;

import ir.mahdi.universityservice.base.service.BaseService;
import ir.mahdi.universityservice.domain.Role;

import java.util.Optional;

public interface RoleService extends BaseService<Role, Integer> {

    Optional<Role> findByName(String username);
}
