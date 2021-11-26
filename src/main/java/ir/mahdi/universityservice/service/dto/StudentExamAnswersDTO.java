package ir.mahdi.universityservice.service.dto;

import ir.mahdi.universityservice.base.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentExamAnswersDTO extends BaseDTO<Long> {
    private long studentId;
    private String studentFullName;
    private String studentUsername;
    private long examId;
    private long examAnswerId;
    private List<String> answers;
}
