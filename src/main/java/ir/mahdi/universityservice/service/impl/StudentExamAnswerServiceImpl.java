package ir.mahdi.universityservice.service.impl;

import ir.mahdi.universityservice.base.service.impl.BaseServiceImpl;
import ir.mahdi.universityservice.domain.StudentExamAnswer;
import ir.mahdi.universityservice.repository.StudentExamAnswerRepository;
import ir.mahdi.universityservice.service.StudentExamAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;


@Service
@Transactional(readOnly = true)
public class StudentExamAnswerServiceImpl extends BaseServiceImpl<StudentExamAnswer, Long, StudentExamAnswerRepository> implements StudentExamAnswerService {

    @Autowired
    public StudentExamAnswerServiceImpl(StudentExamAnswerRepository repository) {
        super(repository);
    }

    @Override
    @Transactional
    public StudentExamAnswer save(StudentExamAnswer studentExamAnswer) {
        return super.save(studentExamAnswer);
    }


    @Override
    @Transactional
    public List<StudentExamAnswer> saveAll(Collection<StudentExamAnswer> studentExamAnswers) {
        return super.saveAll(studentExamAnswers);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        super.deleteById(id);
    }

}
