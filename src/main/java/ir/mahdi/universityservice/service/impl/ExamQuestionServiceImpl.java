package ir.mahdi.universityservice.service.impl;

import ir.mahdi.universityservice.base.service.impl.BaseServiceImpl;
import ir.mahdi.universityservice.domain.ExamQuestion;
import ir.mahdi.universityservice.domain.base.Question;
import ir.mahdi.universityservice.repository.ExamQuestionRepository;
import ir.mahdi.universityservice.service.ExamQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;


@Service
@Transactional(readOnly = true)
public class ExamQuestionServiceImpl extends BaseServiceImpl<ExamQuestion, Long, ExamQuestionRepository> implements ExamQuestionService {

    @Autowired
    public ExamQuestionServiceImpl(ExamQuestionRepository repository) {
        super(repository);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public ExamQuestion save(ExamQuestion question) {
        return super.save(question);
    }


    @Override
    @Transactional
    public List<ExamQuestion> saveAll(Collection<ExamQuestion> questions) {
        return super.saveAll(questions);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public List<ExamQuestion> findAllByExamId(Long examId) {
        return repository.findAllByExamId(examId);
    }

    @Override
    public List<Question> findExamQuestionsByExamId(long examId) {
        return repository.findExamQuestionsByExamId(examId);
    }
}
