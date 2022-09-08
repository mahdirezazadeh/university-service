package ir.mahdi.universityservice.service;

import ir.mahdi.universityservice.base.service.BaseService;
import ir.mahdi.universityservice.domain.*;
import ir.mahdi.universityservice.domain.base.Question;

import java.util.List;

public interface QuestionService extends BaseService<Question<?, ?>, Long> {
    List<Question<?, ?>> findQuestionsByCourse(Course course);

    Exam getExamById(Long examId);

    List<ExamQuestion> findAllExamQuestionByExamId(Long examId);

    Course getCourseByExamId(long examId);

    void createQuestionByExamIdQuestionIdScore(long examId, long questionId, int score);

    void saveDescriptiveQuestion(DescriptiveQuestion question, float score, long examId);

    void saveMultiAnswerQuestion(MultipleChoiceQuestion question, String rightAnswer, float score, long examId);

    void deleteExamQuestionById(long examQuestionId);

    void saveQuestionAnswer(long studentExamAnswerId, long questionId, String studentAnswer);

    void saveAnswerScore(long questionId, float teacherGivenScore);
}
