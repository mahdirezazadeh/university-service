package ir.mahdi.universityservice.service.impl;

import ir.mahdi.universityservice.base.service.impl.BaseServiceImpl;
import ir.mahdi.universityservice.domain.Operation;
import ir.mahdi.universityservice.domain.Role;
import ir.mahdi.universityservice.repository.RoleRepository;
import ir.mahdi.universityservice.service.OperationService;
import ir.mahdi.universityservice.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@Service
@Transactional(readOnly = true)
public class RoleServiceImpl extends BaseServiceImpl<Role, Integer, RoleRepository> implements RoleService {

    private final OperationService operationService;

    @Autowired
    public RoleServiceImpl(RoleRepository repository, OperationService operationService) {
        super(repository);
        this.operationService = operationService;
    }

    @Override
    @Transactional
    public Role save(Role operation) {
        return super.save(operation);
    }


    @Override
    @Transactional
    public List<Role> saveAll(Collection<Role> operations) {
        return super.saveAll(operations);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        super.deleteById(id);
    }

    @Override
    public Optional<Role> findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    @Transactional
    public void initData() {
        if (repository.count() == 0) {
            Operation read = operationService.findByName("read").get();
            Operation write = operationService.findByName("write").get();
            Operation update = operationService.findByName("update").get();
            Operation delete = operationService.findByName("delete").get();
            Operation userConfirmation = operationService.findByName("userConfirmation").get();

            Set<Operation> adminOperations = new HashSet<>();
            Set<Operation> teacherOperations = new HashSet<>();
            Set<Operation> studentOperations = new HashSet<>();

            adminOperations.add(read);
            adminOperations.add(write);
            adminOperations.add(update);
            adminOperations.add(delete);
            adminOperations.add(userConfirmation);

            teacherOperations.add(read);
            teacherOperations.add(update);

            studentOperations.add(read);

            repository.save(Role.builder()
                    .name("admin")
                    .operations(adminOperations).build());
            repository.save(Role.builder()
                    .name("teacher")
                    .operations(teacherOperations).build());
            repository.save(Role.builder()
                    .name("student")
                    .operations(studentOperations).build());
        }
    }

}
