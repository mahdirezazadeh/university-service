package ir.mahdi.universityservice.service.impl;

import ir.mahdi.universityservice.base.service.impl.BaseServiceImpl;
import ir.mahdi.universityservice.domain.Admin;
import ir.mahdi.universityservice.domain.base.User;
import ir.mahdi.universityservice.domain.enumeration.Gender;
import ir.mahdi.universityservice.repository.AdminRepository;
import ir.mahdi.universityservice.service.AdminService;
import ir.mahdi.universityservice.service.RoleService;
import ir.mahdi.universityservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;


@Service
@Transactional(readOnly = true)
@Slf4j
public class AdminServiceImpl extends BaseServiceImpl<Admin, Long, AdminRepository> implements AdminService {

    private final RoleService roleRepository;

    @Autowired
    public AdminServiceImpl(AdminRepository repository, RoleService roleRepository) {
        super(repository);
        this.roleRepository = roleRepository;
    }

    @Autowired
    private UserService userService;

    @Override
    @Transactional
    public Admin save(Admin admin) {
        return super.save(admin);
    }


    @Override
    @Transactional
    public List<Admin> saveAll(Collection<Admin> admins){
        return super.saveAll(admins);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    @Transactional
    public void initUsers() {
        if (repository.count() == 0) {
            try {
                Admin admin = new Admin();
                admin.setConfirmed(true);
                admin.setUsername("adminMahdi");
                admin.setFirstName("Mahdi");
                admin.setLastName("Rezazadeh");
                admin.setPassword("1234");
                admin.setGender(Gender.MALE);
                admin.setEmail("riza.rs2000@gmail.com");
                admin.setPhoneNumber("09145810328");
                admin.setCreateDate(LocalDateTime.now());
                admin.getRoles().add(roleRepository.findByName("admin").get());
                try {
                    repository.save(admin);
                } catch (Exception e) {
                    log.error("can not save initializer admin to database!");
                    throw e;
                }
            } catch (Exception e) {
                log.error("Can not build initializer admin!");
                throw e;
            }
        }
    }

    @Override
    public List<User> loadAllUsers() {
        return userService.findAll();
    }
}
