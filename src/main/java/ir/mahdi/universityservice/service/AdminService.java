package ir.mahdi.universityservice.service;

import ir.mahdi.universityservice.base.service.BaseService;
import ir.mahdi.universityservice.domain.Admin;

import java.util.Optional;

public interface AdminService extends BaseService<Admin, Long> {

    Optional<Admin> findByUsername(String username);

    <P> P findAdminByUsername(String username, Class<P> clazz);

    void initUsers();
}
