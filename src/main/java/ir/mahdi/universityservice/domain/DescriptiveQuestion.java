package ir.mahdi.universityservice.domain;

import ir.mahdi.universityservice.domain.base.Question;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Setter
@Getter
public class DescriptiveQuestion extends Question<String, String> {

    @Builder
    public DescriptiveQuestion() {
        setQuestionType("Descriptive");
    }

    @Builder
    public DescriptiveQuestion(String title, Course course, String question, String answer) {
        super(title, course, "Descriptive", question, answer);
    }
}
