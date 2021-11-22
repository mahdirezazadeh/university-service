package ir.mahdi.universityservice.service;

import ir.mahdi.universityservice.base.service.BaseService;
import ir.mahdi.universityservice.domain.Student;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface StudentService extends BaseService<Student, Long> {

    Optional<Student> findByUsername(String username);

    <P> P findStudentByUsername(String username, Class<P> clazz);

    List<Student> findOtherStudents(Collection<Student> studentsInCourse);
}
