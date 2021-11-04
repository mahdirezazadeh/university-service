package ir.mahdi.universityservice.service;

import ir.mahdi.universityservice.base.service.BaseService;
import ir.mahdi.universityservice.domain.Teacher;

import java.util.Optional;

public interface TeacherService extends BaseService<Teacher, Long> {

    Optional<Teacher> findByUsername(String username);

    <P> P findTeacherByUsername(String username, Class<P> clazz);
}
