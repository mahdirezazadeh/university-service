package ir.mahdi.universityservice.domain;

import ir.mahdi.universityservice.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentQuestionAnswer extends BaseEntity<Long> {
    private long examQuestionId;

    private String answer;

    private float maxScore;

    private float score;

    public StudentQuestionAnswer(ExamQuestion question) {
        examQuestionId = question.getId();
        answer = "";
        score = 0;
        maxScore = question.getScore();
    }

    public void setScore(float score) {
        if (score < 0)
            throw new RuntimeException("score can not be negative!");
        if (score > maxScore)
            throw new RuntimeException("score can not be more than question score!");
        this.score = score;
    }
}
