package ir.mahdi.universityservice.init;

import ir.mahdi.universityservice.service.AdminService;
import ir.mahdi.universityservice.service.OperationService;
import ir.mahdi.universityservice.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class DataInitializer {

    private final AdminService adminservice;
    private final OperationService operationService;
    private final RoleService roleService;

    @PostConstruct
    public void initData() {
        initOperations();
        initRoles();
        initUsers();
    }

    private void initRoles() {
        roleService.initData();
    }

    private void initOperations() {
        operationService.initData();
    }

    private void initUsers() {
        adminservice.initUsers();
    }

}
