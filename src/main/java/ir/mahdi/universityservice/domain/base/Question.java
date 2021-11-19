package ir.mahdi.universityservice.domain.base;

import ir.mahdi.universityservice.base.BaseEntity;
import ir.mahdi.universityservice.domain.Course;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Question<QUESTION, ANSWER> extends BaseEntity<Long> {
    private static final String TITLE = "title";
    private static final String QUESTION_TYPE = "question_type";

    @Column(name = TITLE, length = 100, nullable = false)
    private String title;

    @ManyToOne
    private Course course;

    @Column(name = QUESTION_TYPE)
    private String questionType;

    private QUESTION question;

    private ANSWER answer;
}
