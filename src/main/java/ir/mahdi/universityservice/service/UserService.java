package ir.mahdi.universityservice.service;

import ir.mahdi.universityservice.domain.base.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User save(User user);

    List<User> findAll();

    void delete(User user);

    Optional<User> findByUsername(String username);

    <P> P findUserByUsername(String username, Class<P> clazz);
}
