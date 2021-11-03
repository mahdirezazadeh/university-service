package ir.mahdi.universityservice.service.impl;

import ir.mahdi.universityservice.domain.Student;
import ir.mahdi.universityservice.repository.StudentRepository;
import ir.mahdi.universityservice.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Override
    @Transactional
    public Student save(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    @Transactional
    public void delete(Student student) {
        studentRepository.delete(student);
    }

    @Override
    public Optional<Student> findByUsername(String username) {
        return studentRepository.findStudentByUsername(username);
    }

    @Override
    public <P> P findStudentByUsername(String username, Class<P> clazz) {
        return studentRepository.findStudentByUsername(username, clazz);
    }
}
