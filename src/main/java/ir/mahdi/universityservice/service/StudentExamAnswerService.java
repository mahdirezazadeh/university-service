package ir.mahdi.universityservice.service;

import ir.mahdi.universityservice.base.service.BaseService;
import ir.mahdi.universityservice.domain.Course;
import ir.mahdi.universityservice.domain.Exam;
import ir.mahdi.universityservice.domain.Student;
import ir.mahdi.universityservice.domain.StudentExamAnswer;

import java.util.List;

public interface StudentExamAnswerService extends BaseService<StudentExamAnswer, Long> {
    List<Exam> findExamsByStudentAndCourse(Student student, Course course);
}
