package ir.mahdi.universityservice.service;

import ir.mahdi.universityservice.base.service.BaseService;
import ir.mahdi.universityservice.domain.ExamQuestion;
import ir.mahdi.universityservice.domain.base.Question;

import java.util.List;

public interface ExamQuestionService extends BaseService<ExamQuestion, Long> {
    List<ExamQuestion> findAllByExamId(Long examId);

    List<Question> findExamQuestionsByExamId(long examId);

    ExamQuestion createQuestionByExamIdQuestionIdScore(long examId, long questionId, int score);
}
