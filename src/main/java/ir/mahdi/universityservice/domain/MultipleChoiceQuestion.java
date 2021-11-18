package ir.mahdi.universityservice.domain;

import ir.mahdi.universityservice.domain.base.Question;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
public class MultipleChoiceQuestion extends Question<String, String> {

    private static final String QUESTION_TYPE = "MultipleChoice";

    @ElementCollection
    private Set<String> choices = new HashSet<>();

    @Builder
    public MultipleChoiceQuestion() {
        setQuestionType(QUESTION_TYPE);
    }

    @Builder
    public MultipleChoiceQuestion(Set<String> choices) {
        this.choices = choices;
        setQuestionType(QUESTION_TYPE);
    }

    @Builder
    public MultipleChoiceQuestion(String title, Course course, String s, String s2, Set<String> choices) {
        super(title, course, QUESTION_TYPE, s, s2);
        this.choices = choices;
    }
}
