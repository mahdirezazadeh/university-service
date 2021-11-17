package ir.mahdi.universityservice.repository;

import ir.mahdi.universityservice.domain.MultipleChoiceQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MultipleChoiceQuestionRepository extends JpaRepository<MultipleChoiceQuestion, Long> {

}
