package ir.mahdi.universityservice.domain.base;

import ir.mahdi.universityservice.base.BaseEntity;
import ir.mahdi.universityservice.domain.Course;
import ir.mahdi.universityservice.domain.ExamQuestion;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class Question<QUESTION, ANSWER> extends BaseEntity<Long> {
    private static final String TITLE = "title";
    private static final String QUESTION_TYPE = "question_type";

    @Column(name = TITLE, length = 100, nullable = false)
    private String title;

    @ManyToOne
    private Course course;

    @Column(name = QUESTION_TYPE)
    private String questionType;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<ExamQuestion> examQuestion;

    private QUESTION question;

    private ANSWER answer;

    public Question(String title, Course course, String questionType, QUESTION question, ANSWER answer) {
        this.title = title;
        this.course = course;
        this.questionType = questionType;
        this.question = question;
        this.answer = answer;
    }
}
