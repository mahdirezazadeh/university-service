package ir.mahdi.universityservice.service.impl;

import ir.mahdi.universityservice.base.service.impl.BaseServiceImpl;
import ir.mahdi.universityservice.domain.StudentQuestionAnswer;
import ir.mahdi.universityservice.repository.StudentQuestionAnswerRepository;
import ir.mahdi.universityservice.service.StudentQuestionAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;


@Service
@Transactional(readOnly = true)
public class StudentQuestionAnswerServiceImpl extends BaseServiceImpl<StudentQuestionAnswer, Long, StudentQuestionAnswerRepository> implements StudentQuestionAnswerService {

    @Autowired
    public StudentQuestionAnswerServiceImpl(StudentQuestionAnswerRepository repository) {
        super(repository);
    }

    @Override
    @Transactional
    public StudentQuestionAnswer save(StudentQuestionAnswer studentQuestionAnswer) {
        return super.save(studentQuestionAnswer);
    }


    @Override
    @Transactional
    public List<StudentQuestionAnswer> saveAll(Collection<StudentQuestionAnswer> studentQuestionAnswers) {
        return super.saveAll(studentQuestionAnswers);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        super.deleteById(id);
    }

}
