package ir.mahdi.universityservice.service;

import ir.mahdi.universityservice.base.service.BaseService;
import ir.mahdi.universityservice.domain.Course;
import ir.mahdi.universityservice.domain.base.Question;

import java.util.List;

public interface QuestionService extends BaseService<Question<?, ?>, Long> {
    List<Question<?, ?>> findQuestionsByExam(Course course);
}
