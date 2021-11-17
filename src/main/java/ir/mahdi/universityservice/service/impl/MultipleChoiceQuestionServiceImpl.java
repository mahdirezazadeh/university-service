package ir.mahdi.universityservice.service.impl;

import ir.mahdi.universityservice.base.service.impl.BaseServiceImpl;
import ir.mahdi.universityservice.domain.MultipleChoiceQuestion;
import ir.mahdi.universityservice.repository.MultipleChoiceQuestionRepository;
import ir.mahdi.universityservice.service.MultipleChoiceQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;


@Service
@Transactional(readOnly = true)
public class MultipleChoiceQuestionServiceImpl extends BaseServiceImpl<MultipleChoiceQuestion, Long, MultipleChoiceQuestionRepository> implements MultipleChoiceQuestionService {

    @Autowired
    public MultipleChoiceQuestionServiceImpl(MultipleChoiceQuestionRepository repository) {
        super(repository);
    }

    @Override
    @Transactional
    public MultipleChoiceQuestion save(MultipleChoiceQuestion descriptiveQuestion) {
        return super.save(descriptiveQuestion);
    }


    @Override
    @Transactional
    public List<MultipleChoiceQuestion> saveAll(Collection<MultipleChoiceQuestion> descriptiveQuestions) {
        return super.saveAll(descriptiveQuestions);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        super.deleteById(id);
    }

}
