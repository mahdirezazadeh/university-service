package ir.mahdi.universityservice.service;

import ir.mahdi.universityservice.domain.Teacher;

import java.util.List;
import java.util.Optional;

public interface TeacherService {
    Teacher save(Teacher teacher);

    List<Teacher> findAll();

    void delete(Teacher teacher);

    Optional<Teacher> findByUsername(String username);

    <P> P findTeacherByUsername(String username, Class<P> clazz);
}
