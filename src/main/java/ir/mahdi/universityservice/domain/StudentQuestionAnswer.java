package ir.mahdi.universityservice.domain;

import ir.mahdi.universityservice.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentQuestionAnswer extends BaseEntity<Long> {
    private static final String EXAM_QUESTION_ID = "exam_question_id";
    private static final String ANSWER = "answer";
    private static final String MAX_SCORE = "max_score";
    private static final String SCORE = "score";


    @Column(name = EXAM_QUESTION_ID)
    private long examQuestionId;

    @Column(name = ANSWER, length = 2000)
    private String answer;

    @Column(name = MAX_SCORE)
    private float maxScore;

    @Column(name = SCORE)
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
