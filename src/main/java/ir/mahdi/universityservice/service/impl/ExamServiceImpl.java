package ir.mahdi.universityservice.service.impl;

import ir.mahdi.universityservice.base.service.impl.BaseServiceImpl;
import ir.mahdi.universityservice.domain.Course;
import ir.mahdi.universityservice.domain.Exam;
import ir.mahdi.universityservice.repository.ExamRepository;
import ir.mahdi.universityservice.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;


@Service
@Transactional(readOnly = true)
public class ExamServiceImpl extends BaseServiceImpl<Exam, Long, ExamRepository> implements ExamService {

    @Autowired
    public ExamServiceImpl(ExamRepository repository) {
        super(repository);
    }

    @Override
    @Transactional
    public Exam save(Exam exam) {
        return super.save(exam);
    }


    @Override
    @Transactional
    public List<Exam> saveAll(Collection<Exam> exams) {
        return super.saveAll(exams);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Transactional
    @Override
    public boolean softDelete(long examId) {
        try {
            Optional<Exam> examOptional = findById(examId);
            if (examOptional.isPresent()) {
                Exam exam = examOptional.get();
                exam.setDeleted(true);
                repository.save(exam);
                return true;
            }

        } catch (Exception e) {
            return false;
        }
        return false;
    }

    @Override
    @Transactional
    public List<Exam> findExamsByCourse(Course course) {
        return repository.findExamByCourse(course);
    }

}
