package ir.mahdi.universityservice.service.impl;

import ir.mahdi.universityservice.base.service.impl.BaseServiceImpl;
import ir.mahdi.universityservice.domain.DescriptiveQuestion;
import ir.mahdi.universityservice.repository.DescriptiveQuestionRepository;
import ir.mahdi.universityservice.service.DescriptiveQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;


@Service
@Transactional(readOnly = true)
public class DescriptiveQuestionServiceImpl extends BaseServiceImpl<DescriptiveQuestion, Long, DescriptiveQuestionRepository> implements DescriptiveQuestionService {

    @Autowired
    public DescriptiveQuestionServiceImpl(DescriptiveQuestionRepository repository) {
        super(repository);
    }

    @Override
    @Transactional
    public DescriptiveQuestion save(DescriptiveQuestion descriptiveQuestion) {
        return super.save(descriptiveQuestion);
    }


    @Override
    @Transactional
    public List<DescriptiveQuestion> saveAll(Collection<DescriptiveQuestion> descriptiveQuestions) {
        return super.saveAll(descriptiveQuestions);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        super.deleteById(id);
    }

}
