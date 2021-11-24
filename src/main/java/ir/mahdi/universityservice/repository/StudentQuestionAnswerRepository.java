package ir.mahdi.universityservice.repository;

import ir.mahdi.universityservice.domain.StudentQuestionAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentQuestionAnswerRepository extends JpaRepository<StudentQuestionAnswer, Long> {

}
