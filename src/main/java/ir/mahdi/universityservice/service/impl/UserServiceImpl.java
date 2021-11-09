package ir.mahdi.universityservice.service.impl;

import ir.mahdi.universityservice.base.service.impl.BaseServiceImpl;
import ir.mahdi.universityservice.domain.base.User;
import ir.mahdi.universityservice.repository.UserRepository;
import ir.mahdi.universityservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;


@Service
@Transactional(readOnly = true)
public class UserServiceImpl extends BaseServiceImpl<User, Long, UserRepository> implements UserService {

    @Autowired
    public UserServiceImpl(UserRepository repository) {
        super(repository);
    }

    @Override
    @Transactional
    public User save(User user){
        return super.save(user);
    }


    @Override
    @Transactional
    public List<User> saveAll(Collection<User> users){
        return super.saveAll(users);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return repository.findUserByUsername(username);
    }

    @Override
    public <P> P findUserByUsername(String username, Class<P> clazz) {
        return repository.findUserByUsername(username, clazz);
    }

    @Override
    @Transactional
    public boolean activateUserById(Long id) {
        Optional<User> user = this.findById(id);
        if (user.isPresent() && !user.get().isConfirmed()) {
            User user1 = user.get();
            user1.setConfirmed(true);
            save(user1);
            return true;
        }
        return false;
    }


    @Override
    @Transactional
    public boolean deactivateUserById(Long id) {
        Optional<User> user = this.findById(id);
        if (user.isPresent() && user.get().isConfirmed()) {
            User user1 = user.get();
            user1.setConfirmed(false);
            save(user1);
            return true;
        }
        return false;
    }

//    @Override
//    public List<User> findByUserSearchDTO(UserSearchDTO userSearch) {
//
//        return repository.findByUserSearchDTO(userSearch);
//    }

}
