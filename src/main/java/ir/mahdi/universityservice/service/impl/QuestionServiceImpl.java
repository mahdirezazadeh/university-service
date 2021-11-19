package ir.mahdi.universityservice.service.impl;

import ir.mahdi.universityservice.base.service.impl.BaseServiceImpl;
import ir.mahdi.universityservice.domain.Course;
import ir.mahdi.universityservice.domain.base.Question;
import ir.mahdi.universityservice.repository.QuestionRepository;
import ir.mahdi.universityservice.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;


@Service
@Transactional(readOnly = true)
public class QuestionServiceImpl extends BaseServiceImpl<Question<?, ?>, Long, QuestionRepository> implements QuestionService {


    @Autowired
    public QuestionServiceImpl(QuestionRepository repository) {
        super(repository);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Question<?, ?> save(Question<?, ?> question) {
        return super.save(question);
    }


    @Override
    @Transactional
    public List<Question<?, ?>> saveAll(Collection<Question<?, ?>> questions) {
        return super.saveAll(questions);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public List<Question<?, ?>> findQuestionsByExam(Course course) {
        return repository.findAllByCourse(course);
    }
}
