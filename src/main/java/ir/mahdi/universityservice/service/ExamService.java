package ir.mahdi.universityservice.service;

import ir.mahdi.universityservice.base.service.BaseService;
import ir.mahdi.universityservice.domain.Course;
import ir.mahdi.universityservice.domain.Exam;

import java.util.List;

public interface ExamService extends BaseService<Exam, Long> {
    boolean softDelete(long examId);

    public List<Exam> findExamsByCourse(Course course);
}
