package ir.mahdi.universityservice.service;

import ir.mahdi.universityservice.base.service.BaseService;
import ir.mahdi.universityservice.domain.base.User;

import java.util.Optional;

public interface UserService extends BaseService<User, Long> {

    Optional<User> findByUsername(String username);

    boolean activateUserById(Long id);

    boolean deactivateUserById(Long id);

    boolean isUsernameAlreadyTaken(String username);
}
