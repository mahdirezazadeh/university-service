package ir.mahdi.universityservice.service.impl;

import ir.mahdi.universityservice.base.service.impl.BaseServiceImpl;
import ir.mahdi.universityservice.domain.MultipleChoiceQuestion;
import ir.mahdi.universityservice.domain.StudentExamAnswer;
import ir.mahdi.universityservice.domain.StudentQuestionAnswer;
import ir.mahdi.universityservice.domain.base.Question;
import ir.mahdi.universityservice.repository.StudentQuestionAnswerRepository;
import ir.mahdi.universityservice.service.ExamQuestionService;
import ir.mahdi.universityservice.service.StudentExamAnswerService;
import ir.mahdi.universityservice.service.StudentQuestionAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;


@Service
@Transactional(readOnly = true)
public class StudentQuestionAnswerServiceImpl extends BaseServiceImpl<StudentQuestionAnswer, Long, StudentQuestionAnswerRepository> implements StudentQuestionAnswerService {

    private final StudentExamAnswerService examAnswerService;

    private final ExamQuestionService examQuestionService;

    @Autowired
    public StudentQuestionAnswerServiceImpl(StudentQuestionAnswerRepository repository, StudentExamAnswerService examAnswerService, ExamQuestionService examQuestionService) {
        super(repository);
        this.examAnswerService = examAnswerService;
        this.examQuestionService = examQuestionService;
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

    @Override
    @Transactional
    public void saveAnswer(long studentExamAnswerId, long questionId, String studentAnswer) {
        StudentQuestionAnswer studentQuestionAnswer = findById(questionId).get();
//        if(studentQuestionAnswer.get!=studentExamAnswerId)
//            throw new RuntimeException("question is unavailable");

        try {
            StudentExamAnswer studentExamAnswer = examAnswerService.findById(studentExamAnswerId).get();
            if (studentExamAnswer.getEndTime().isBefore(LocalDateTime.now()))
                throw new RuntimeException("exam is unavailable");
        } catch (Exception e) {
            throw new RuntimeException("exam is unavailable");
        }
        studentQuestionAnswer.setAnswer(studentAnswer);
        long examQuestionId = studentQuestionAnswer.getExamQuestionId();
        Question<?, ?> question = examQuestionService.findById(examQuestionId).get().getQuestion();
        if (question.getQuestionType().equals(MultipleChoiceQuestion.getQuestionTypeString())) {
            if (question.getAnswer().equals(studentAnswer)) {
                studentQuestionAnswer.setScore(studentQuestionAnswer.getMaxScore());
            } else studentQuestionAnswer.setScore(0);
        }
        save(studentQuestionAnswer);
    }
}
