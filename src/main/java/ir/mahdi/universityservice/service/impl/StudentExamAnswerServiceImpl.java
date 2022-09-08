package ir.mahdi.universityservice.service.impl;

import ir.mahdi.universityservice.base.service.impl.BaseServiceImpl;
import ir.mahdi.universityservice.domain.*;
import ir.mahdi.universityservice.exceptions.ExamHaveBeenDoneByStudent;
import ir.mahdi.universityservice.repository.StudentExamAnswerRepository;
import ir.mahdi.universityservice.service.ExamQuestionService;
import ir.mahdi.universityservice.service.ExamService;
import ir.mahdi.universityservice.service.StudentExamAnswerService;
import ir.mahdi.universityservice.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;


@Service
@Transactional(readOnly = true)
public class StudentExamAnswerServiceImpl extends BaseServiceImpl<StudentExamAnswer, Long, StudentExamAnswerRepository> implements StudentExamAnswerService {

    @Autowired
    private StudentService studentService;

    @Autowired
    private ExamService examService;

    @Autowired
    private ExamQuestionService examQuestionService;

    @Autowired
    public StudentExamAnswerServiceImpl(StudentExamAnswerRepository repository) {
        super(repository);
    }

    @Override
    @Transactional
    public StudentExamAnswer save(StudentExamAnswer studentExamAnswer) {
        return super.save(studentExamAnswer);
    }


    @Override
    @Transactional
    public List<StudentExamAnswer> saveAll(Collection<StudentExamAnswer> studentExamAnswers) {
        return super.saveAll(studentExamAnswers);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public List<Exam> findExamsByStudentAndCourse(Student student, Course course) {
        return repository.findAllExamsByStudentAndCourse(student, course);
    }

    @Override
    @Transactional
    public StudentExamAnswer startExam(Exam exam, String username) {
        Student student = studentService.findByUsername(username);

        StudentExamAnswer studentExamAnswer = repository.findByExamAndStudent(exam, student);
        if (studentExamAnswer != null && studentExamAnswer.getEndTime().isAfter(LocalDateTime.now()))
            return studentExamAnswer;

        studentExamAnswer = new StudentExamAnswer();
        studentExamAnswer.setStudent(student);
        studentExamAnswer.setExam(exam);
        studentExamAnswer.setEndTime(exam.getDuration());

        List<ExamQuestion> questions = examQuestionService.findAllByExamId(exam.getId());

        for (ExamQuestion question : questions) {
            StudentQuestionAnswer studentQuestionAnswer = new StudentQuestionAnswer(question);
            studentExamAnswer.getStudentAnswers().add(studentQuestionAnswer);
        }

        try {
            studentExamAnswer = save(studentExamAnswer);
        } catch (Exception e) {
            throw new ExamHaveBeenDoneByStudent();
        }
        return studentExamAnswer;

    }

    @Override
    public List<StudentExamAnswer> findByExamId(long examId) {
        Exam exam = examService.findById(examId).get();
        List<StudentExamAnswer> studentExamAnswers = repository.findAllByExam(exam);
        return studentExamAnswers;

    }
}
