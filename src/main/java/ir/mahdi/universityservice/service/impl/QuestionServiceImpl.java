package ir.mahdi.universityservice.service.impl;

import ir.mahdi.universityservice.base.service.impl.BaseServiceImpl;
import ir.mahdi.universityservice.domain.*;
import ir.mahdi.universityservice.domain.base.Question;
import ir.mahdi.universityservice.exceptions.ItemDoesNotExistException;
import ir.mahdi.universityservice.repository.QuestionRepository;
import ir.mahdi.universityservice.service.*;
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
    private ExamService examService;
    @Autowired
    private ExamQuestionService examQuestionService;
    @Autowired
    private DescriptiveQuestionService descriptiveQuestionService;
    @Autowired
    private MultipleChoiceQuestionService multipleChoiceQuestionService;
    @Autowired
    private StudentQuestionAnswerService studentQuestionAnswerService;

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
    public List<Question<?, ?>> findQuestionsByCourse(Course course) {
        return repository.findAllByCourse(course);
    }

    @Override
    public Exam getExamById(Long examId) {
        return examService.findById(examId).orElseThrow(() -> new ItemDoesNotExistException("Exam"));
    }

    @Override
    public List<ExamQuestion> findAllExamQuestionByExamId(Long examId) {
        return examQuestionService.findAllByExamId(examId);
    }

    @Override
    public Course getCourseByExamId(long examId) {
        Exam exam = getExamById(examId);
        return exam.getCourse();
    }

    @Override
    public void createQuestionByExamIdQuestionIdScore(long examId, long questionId, int score) {
        examQuestionService.createQuestionByExamIdQuestionIdScore(examId, questionId, score);
    }

    @Override
    public void saveDescriptiveQuestion(DescriptiveQuestion question, float score, long examId) {
        Course course = getCourseByExamId(examId);
        question.setCourse(course);

        descriptiveQuestionService.save(question);

        ExamQuestion examQuestion = new ExamQuestion(
                getExamById(examId),
                question,
                score
        );
        examQuestionService.save(examQuestion);
    }

    @Override
    public void saveMultiAnswerQuestion(MultipleChoiceQuestion question, String rightAnswer, float score, long examId) {
        Course course = getCourseByExamId(examId);
        question.setCourse(course);
        question.setAnswer(rightAnswer);
        multipleChoiceQuestionService.save(question);

        ExamQuestion examQuestion = new ExamQuestion(
                getExamById(examId),
                question,
                score
        );

        examQuestionService.save(examQuestion);
    }

    @Override
    public void deleteExamQuestionById(long examQuestionId) {
        examQuestionService.deleteById(examQuestionId);
    }

    @Override
    public void saveQuestionAnswer(long studentExamAnswerId, long questionId, String studentAnswer) {
        studentQuestionAnswerService.saveAnswer(studentExamAnswerId, questionId, studentAnswer);
    }

    @Override
    public void saveAnswerScore(long questionId, float teacherGivenScore) {
        studentQuestionAnswerService.saveAnswerScore(questionId, teacherGivenScore);
    }
}
