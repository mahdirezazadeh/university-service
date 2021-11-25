package ir.mahdi.universityservice.domain;

import ir.mahdi.universityservice.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentQuestionAnswer extends BaseEntity<Long> {
    @ManyToOne
    private ExamQuestion examQuestion;

    private String answer;

    private float score;

    public StudentQuestionAnswer(ExamQuestion question) {
        examQuestion = question;
        answer = "";
        score = 0;
    }

    public void setScore(float score) {
        if (score < 0)
            throw new RuntimeException("score can not be negative!");
        if (score > examQuestion.getScore())
            throw new RuntimeException("score can not be more than question score!");
        this.score = score;
    }
}
