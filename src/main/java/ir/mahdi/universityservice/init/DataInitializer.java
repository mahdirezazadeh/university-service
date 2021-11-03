package ir.mahdi.universityservice.init;

import ir.mahdi.universityservice.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class DataInitializer {

    private final AdminService adminservice;

    @PostConstruct
    public void initData() {
        initUsers();
    }

    private void initUsers() {
        adminservice.initUsers();
    }

}
