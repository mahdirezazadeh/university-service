package ir.mahdi.universityservice.service.impl;

import ir.mahdi.universityservice.base.service.impl.BaseServiceImpl;
import ir.mahdi.universityservice.domain.Admin;
import ir.mahdi.universityservice.domain.enumeration.Gender;
import ir.mahdi.universityservice.repository.AdminRepository;
import ir.mahdi.universityservice.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;


@Service
@Transactional(readOnly = true)
public class AdminServiceImpl extends BaseServiceImpl<Admin, Long, AdminRepository> implements AdminService {

    @Autowired
    public AdminServiceImpl(AdminRepository repository) {
        super(repository);
    }

    @Override
    @Transactional
    public Admin save(Admin admin){
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
    public Optional<Admin> findByUsername(String username) {
        return repository.findAdminByUsername(username);
    }

    @Override
    public <P> P findAdminByUsername(String username, Class<P> clazz) {
        return repository.findAdminByUsername(username, clazz);
    }

    @Override
    @Transactional
    public void initUsers() {
        if (repository.count() == 0) {
            Admin admin = Admin.builder().build();
            admin.setActive(true);
            admin.setConfirmed(true);
            admin.setDeleted(false);
            admin.setUsername("adminMahdi");
            admin.setPassword("1234");
            admin.setGender(Gender.MALE);
            admin.setEmail("riza.rs2000@gmail.com");
            admin.setCreateDate(LocalDateTime.now());
            repository.save(admin);
        }
    }
}
