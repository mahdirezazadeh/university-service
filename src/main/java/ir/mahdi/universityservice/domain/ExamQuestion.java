package ir.mahdi.universityservice.domain;

import ir.mahdi.universityservice.base.BaseEntity;
import ir.mahdi.universityservice.domain.base.Question;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(uniqueConstraints = {
        @UniqueConstraint(name = "uc_exam_question_exam_id", columnNames = {"exam_id", "question_id"})
})
public class ExamQuestion extends BaseEntity<Long> {

    @ManyToOne
    private Exam exam;

    @ManyToOne
    private Question<?, ?> question;

    private int score;
}
