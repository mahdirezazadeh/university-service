package ir.mahdi.universityservice.service;

import ir.mahdi.universityservice.domain.Admin;

import java.util.List;
import java.util.Optional;

public interface AdminService {
    Admin save(Admin admin);

    List<Admin> findAll();

    void delete(Admin admin);

    Optional<Admin> findByUsername(String username);

    <P> P findAdminByUsername(String username, Class<P> clazz);

    void initUsers();
}
