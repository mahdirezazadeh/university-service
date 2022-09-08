package ir.mahdi.universityservice.service.impl;

import ir.mahdi.universityservice.base.service.impl.BaseServiceImpl;
import ir.mahdi.universityservice.domain.Course;
import ir.mahdi.universityservice.domain.Exam;
import ir.mahdi.universityservice.domain.StudentExamAnswer;
import ir.mahdi.universityservice.domain.base.Question;
import ir.mahdi.universityservice.exceptions.ItemDoesNotExistException;
import ir.mahdi.universityservice.repository.ExamRepository;
import ir.mahdi.universityservice.service.CourseService;
import ir.mahdi.universityservice.service.ExamService;
import ir.mahdi.universityservice.service.QuestionService;
import ir.mahdi.universityservice.service.StudentExamAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Transactional(readOnly = true)
public class ExamServiceImpl extends BaseServiceImpl<Exam, Long, ExamRepository> implements ExamService {

    @Autowired
    private QuestionService questionService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private ExamService examService;
    @Autowired
    private StudentExamAnswerService studentExamAnswerService;

    @Autowired
    public ExamServiceImpl(ExamRepository repository) {
        super(repository);
    }

    @Override
    @Transactional
    public Exam save(Exam exam) {
        return super.save(exam);
    }


    @Override
    @Transactional
    public List<Exam> saveAll(Collection<Exam> exams) {
        return super.saveAll(exams);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Transactional
    @Override
    public boolean softDelete(long examId) {
        try {
            Optional<Exam> examOptional = findById(examId);
            if (examOptional.isPresent()) {
                Exam exam = examOptional.get();
                exam.setDeleted(true);
                repository.save(exam);
                return true;
            }

        } catch (Exception e) {
            return false;
        }
        return false;
    }

    @Override
    @Transactional
    public List<Exam> findExamsByCourse(Course course) {
        return repository.findExamByCourse(course);
    }

    @Override
    @Transactional
    public Exam edit(long examId, Exam examAfter) {
        Exam exam = repository.findById(examId).orElseThrow(() -> new ItemDoesNotExistException("Exam"));
        exam.setDescription(examAfter.getDescription());
        exam.setTitle(examAfter.getTitle());
        exam.setDuration(examAfter.getDuration());
        return repository.save(exam);
    }
/*
    @Override
    @Transactional
    public ExamQuestion addQuestionByExamId(long examId, Question question, int score) {
        Question<?, ?> question1 = questionService.save(question);
        ExamQuestion examQuestion = new ExamQuestion(findById(examId).get(), question1, score);
        ExamQuestion examQuestion1 = examQuestionService.save(examQuestion);
        return examQuestion1;
    }*/

    @Override
    public List<Exam> findAllByCourseAndNotExams(Course course, List<Exam> doneExams) {
        List<Long> ids = doneExams.stream().map(p -> p.getId()).collect(Collectors.toList());
        if (ids.isEmpty())
            ids.add(0L);
        return repository.findExamByCourseAndIdNotIn(course, ids);
    }

    @Override
    public Course getCourseById(long courseId) {
        return courseService.findById(courseId).orElseThrow(() -> new ItemDoesNotExistException("Course"));
    }

    @Override
    public List<Question<?, ?>> getQuestionBankByExamId(long examId) {
        Exam exam = examService.findById(examId).orElseThrow(() -> new ItemDoesNotExistException("Exam"));
        return questionService.findQuestionsByCourse(exam.getCourse());
    }

    @Override
    public StudentExamAnswer startExamForCurrentUser(Exam exam) {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return studentExamAnswerService.startExam(exam, username);
    }

    @Override
    public List<StudentExamAnswer> findStudentExamAnswerByExamId(long examId) {
        return studentExamAnswerService.findByExamId(examId);
    }

    @Override
    public StudentExamAnswer findStudentExamAnswerById(long examAnswerId) {
        return studentExamAnswerService.findById(examAnswerId).orElseThrow(() -> new ItemDoesNotExistException("Student Answer for exam"));
    }

    @Override
    public void saveStudentExamAnswer(StudentExamAnswer studentExamAnswer) {
        studentExamAnswerService.save(studentExamAnswer);
    }

}
