package ir.mahdi.universityservice.service.dto;

import ir.mahdi.universityservice.base.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentQuestionDTO extends BaseDTO<Long> {
    private float maxScore;
    private float score;
    private String question;
    private String studentAnswer;
    private String questionType;
    private List<String> choices = new ArrayList<>();

    public StudentQuestionDTO(float maxScore, String question, String studentAnswer) {
        this.maxScore = maxScore;
        this.question = question;
        this.studentAnswer = studentAnswer;
    }

    public boolean isAnswerSelected(int choice) {
        try {
            return
                    Integer.parseInt(studentAnswer) == choice;
        } catch (Exception e) {
            return false;
        }
    }
}
