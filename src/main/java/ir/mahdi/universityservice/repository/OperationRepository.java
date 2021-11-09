package ir.mahdi.universityservice.repository;

import ir.mahdi.universityservice.domain.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OperationRepository extends JpaRepository<Operation, Integer> {

    Optional<Operation> findByName(String name);
}
