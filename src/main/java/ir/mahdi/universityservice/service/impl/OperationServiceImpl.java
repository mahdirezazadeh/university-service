package ir.mahdi.universityservice.service.impl;

import ir.mahdi.universityservice.base.service.impl.BaseServiceImpl;
import ir.mahdi.universityservice.domain.Operation;
import ir.mahdi.universityservice.repository.OperationRepository;
import ir.mahdi.universityservice.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;


@Service
@Transactional(readOnly = true)
public class OperationServiceImpl extends BaseServiceImpl<Operation, Integer, OperationRepository> implements OperationService {

    @Autowired
    public OperationServiceImpl(OperationRepository repository) {
        super(repository);
    }

    @Override
    @Transactional
    public Operation save(Operation operation) {
        return super.save(operation);
    }


    @Override
    @Transactional
    public List<Operation> saveAll(Collection<Operation> operations) {
        return super.saveAll(operations);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        super.deleteById(id);
    }

    @Override
    public Optional<Operation> findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    @Transactional
    public void initData() {
        if (repository.count() == 0) {
            repository.save(Operation.builder().name("read").build());
            repository.save(Operation.builder().name("write").build());
            repository.save(Operation.builder().name("delete").build());
            repository.save(Operation.builder().name("update").build());
            repository.save(Operation.builder().name("userConfirmation").build());
        }
    }

}
