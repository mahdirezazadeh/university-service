package ir.mahdi.universityservice.domain;

import ir.mahdi.universityservice.domain.base.Question;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
public class MultipleChoiceQuestion extends Question<String, String> {

    private static final String QUESTION_TYPE = "MultipleChoice";

    @ElementCollection
    private List<String> choices = new ArrayList<>();

    @Builder
    public MultipleChoiceQuestion() {
        setQuestionType(QUESTION_TYPE);
    }

    @Builder
    public MultipleChoiceQuestion(List<String> choices) {
        this.choices = choices;
        setQuestionType(QUESTION_TYPE);
    }

    @Builder
    public MultipleChoiceQuestion(String title, Course course, String question, String answer, List<String> choices) {
        super(title, course, QUESTION_TYPE, question, answer);
        this.choices = choices;
    }

    public static String getQuestionTypeString() {
        return QUESTION_TYPE;
    }
}
