package ir.mahdi.universityservice.service;

import ir.mahdi.universityservice.base.service.BaseService;
import ir.mahdi.universityservice.domain.StudentQuestionAnswer;

public interface StudentQuestionAnswerService extends BaseService<StudentQuestionAnswer, Long> {
    void saveAnswer(long studentExamAnswerId, long questionId, String studentAnswer);

    void saveAnswerScore(long questionId, float teacherGivenScore);
}
