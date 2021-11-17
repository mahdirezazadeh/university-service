package ir.mahdi.universityservice.repository;

import ir.mahdi.universityservice.domain.base.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question<?, ?>, Long> {

}
