package ir.mahdi.universityservice.service.dto;

import ir.mahdi.universityservice.base.BaseDTO;
import ir.mahdi.universityservice.domain.Course;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class QuestionDTO<QUESTION, ANSWER> extends BaseDTO<Long> {
    private String title;
    private Course course;
    private String questionType;
    private QUESTION question;
    private ANSWER answer;
}
