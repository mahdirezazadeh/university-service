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
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Question<QUESTION, ANSWER> extends BaseEntity<Long> {
    private static final String TITLE = "title";

    @Column(name = TITLE, length = 100, nullable = false)
    private String title;

    @ManyToOne
    private Course course;

    private QUESTION question;

    private ANSWER answer;
}
