package ir.mahdi.universityservice.service;

import ir.mahdi.universityservice.base.service.BaseService;
import ir.mahdi.universityservice.domain.Admin;
import ir.mahdi.universityservice.domain.base.User;

import java.util.List;

public interface AdminService extends BaseService<Admin, Long> {
    void initUsers();

    List<User> loadAllUsers();
}
