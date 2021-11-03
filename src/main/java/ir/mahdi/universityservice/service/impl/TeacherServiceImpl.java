package ir.mahdi.universityservice.service.impl;

import ir.mahdi.universityservice.domain.Teacher;
import ir.mahdi.universityservice.repository.TeacherRepository;
import ir.mahdi.universityservice.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;

    @Override
    @Transactional
    public Teacher save(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    @Override
    public List<Teacher> findAll() {
        return teacherRepository.findAll();
    }

    @Override
    @Transactional
    public void delete(Teacher teacher) {
        teacherRepository.delete(teacher);
    }

    @Override
    public Optional<Teacher> findByUsername(String username) {
        return teacherRepository.findTeacherByUsername(username);
    }

    @Override
    public <P> P findTeacherByUsername(String username, Class<P> clazz) {
        return teacherRepository.findTeacherByUsername(username, clazz);
    }
}
