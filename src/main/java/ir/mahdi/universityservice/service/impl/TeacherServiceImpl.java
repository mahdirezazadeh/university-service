package ir.mahdi.universityservice.service.impl;

import ir.mahdi.universityservice.base.service.impl.BaseServiceImpl;
import ir.mahdi.universityservice.domain.Teacher;
import ir.mahdi.universityservice.domain.enumeration.Gender;
import ir.mahdi.universityservice.repository.TeacherRepository;
import ir.mahdi.universityservice.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;


@Service
@Transactional(readOnly = true)
public class TeacherServiceImpl extends BaseServiceImpl<Teacher, Long, TeacherRepository> implements TeacherService {

    @Autowired
    public TeacherServiceImpl(TeacherRepository repository) {
        super(repository);
    }

    @Override
    @Transactional
    public Teacher save(Teacher teacher){
        return super.save(teacher);
    }


    @Override
    @Transactional
    public List<Teacher> saveAll(Collection<Teacher> teachers){
        return super.saveAll(teachers);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public Optional<Teacher> findByUsername(String username) {
        return repository.findTeacherByUsername(username);
    }

    @Override
    public <P> P findTeacherByUsername(String username, Class<P> clazz) {
        return repository.findTeacherByUsername(username, clazz);
    }
}
