package ir.mahdi.universityservice.service;

import ir.mahdi.universityservice.base.service.BaseService;
import ir.mahdi.universityservice.domain.Course;
import ir.mahdi.universityservice.domain.Exam;
import ir.mahdi.universityservice.domain.StudentExamAnswer;
import ir.mahdi.universityservice.domain.base.Question;

import java.util.List;

public interface ExamService extends BaseService<Exam, Long> {
    boolean softDelete(long examId);

    public List<Exam> findExamsByCourse(Course course);

    Exam edit(long examId, Exam examAfter);

    List<Exam> findAllByCourseAndNotExams(Course course, List<Exam> doneExams);

    Course getCourseById(long courseId);

    List<Question<?, ?>> getQuestionBankByExamId(long examId);

    StudentExamAnswer startExamForCurrentUser(Exam exam);

    List<StudentExamAnswer> findStudentExamAnswerByExamId(long examId);

    StudentExamAnswer findStudentExamAnswerById(long examAnswerId);

    void saveStudentExamAnswer(StudentExamAnswer studentExamAnswer);
}
