package ir.mahdi.universityservice.service;

import ir.mahdi.universityservice.base.service.BaseService;
import ir.mahdi.universityservice.domain.Operation;

import java.util.Optional;

public interface OperationService extends BaseService<Operation, Integer> {

    Optional<Operation> findByName(String name);

    void initData();

    int count();
}
