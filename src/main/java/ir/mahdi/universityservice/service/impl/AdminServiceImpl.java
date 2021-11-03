package ir.mahdi.universityservice.service.impl;

import ir.mahdi.universityservice.domain.Admin;
import ir.mahdi.universityservice.domain.enumeration.Gender;
import ir.mahdi.universityservice.repository.AdminRepository;
import ir.mahdi.universityservice.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;

    @Override
    @Transactional
    public Admin save(Admin admin) {
        return adminRepository.save(admin);
    }

    @Override
    public List<Admin> findAll() {
        return adminRepository.findAll();
    }

    @Override
    @Transactional
    public void delete(Admin admin) {
        adminRepository.delete(admin);
    }

    @Override
    public Optional<Admin> findByUsername(String username) {
        return adminRepository.findAdminByUsername(username);
    }

    @Override
    public <P> P findAdminByUsername(String username, Class<P> clazz) {
        return adminRepository.findAdminByUsername(username, clazz);
    }

    @Override
    @Transactional
    public void initUsers() {
        if (adminRepository.count() == 0) {
            Admin admin = Admin.builder().build();
            admin.setActive(true);
            admin.setConfirmed(true);
            admin.setDeleted(false);
            admin.setUsername("adminMahdi");
            admin.setPassword("1234");
            admin.setGender(Gender.MALE);
            admin.setEmail("riza.rs2000@gmail.com");
            admin.setCreateDate(LocalDateTime.now());
            adminRepository.save(admin);
        }
    }
}
