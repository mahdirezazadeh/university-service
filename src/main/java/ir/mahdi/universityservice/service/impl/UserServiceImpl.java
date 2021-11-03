package ir.mahdi.universityservice.service.impl;

import ir.mahdi.universityservice.domain.base.User;
import ir.mahdi.universityservice.repository.UserRepository;
import ir.mahdi.universityservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public <P> P findUserByUsername(String username, Class<P> clazz) {
        return userRepository.findUserByUsername(username, clazz);
    }
}
