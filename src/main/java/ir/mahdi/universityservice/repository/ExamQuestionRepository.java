package ir.mahdi.universityservice.repository;

import ir.mahdi.universityservice.domain.ExamQuestion;
import ir.mahdi.universityservice.domain.base.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ExamQuestionRepository extends JpaRepository<ExamQuestion, Long> {
    @Query("select e from ExamQuestion e where e.exam.id = ?1")
    List<ExamQuestion> findAllByExamId(long examId);

    //    @EntityGraph(attributePaths = {"question"})
    @Query("select e.question from ExamQuestion e where e.exam.id = ?1")
    List<Question> findExamQuestionsByExamId(long examId);
}
