package ir.mahdi.universityservice.service;

import ir.mahdi.universityservice.domain.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    Student save(Student student);

    List<Student> findAll();

    void delete(Student student);

    Optional<Student> findByUsername(String username);

    <P> P findStudentByUsername(String username, Class<P> clazz);
}
