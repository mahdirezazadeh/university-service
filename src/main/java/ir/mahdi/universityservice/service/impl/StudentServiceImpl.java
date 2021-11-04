package ir.mahdi.universityservice.service.impl;

import ir.mahdi.universityservice.base.service.impl.BaseServiceImpl;
import ir.mahdi.universityservice.domain.Student;
import ir.mahdi.universityservice.domain.enumeration.Gender;
import ir.mahdi.universityservice.repository.StudentRepository;
import ir.mahdi.universityservice.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;


@Service
@Transactional(readOnly = true)
public class StudentServiceImpl extends BaseServiceImpl<Student, Long, StudentRepository> implements StudentService {

    @Autowired
    public StudentServiceImpl(StudentRepository repository) {
        super(repository);
    }

    @Override
    @Transactional
    public Student save(Student student){
        return super.save(student);
    }


    @Override
    @Transactional
    public List<Student> saveAll(Collection<Student> students){
        return super.saveAll(students);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public Optional<Student> findByUsername(String username) {
        return repository.findStudentByUsername(username);
    }

    @Override
    public <P> P findStudentByUsername(String username, Class<P> clazz) {
        return repository.findStudentByUsername(username, clazz);
    }
}
