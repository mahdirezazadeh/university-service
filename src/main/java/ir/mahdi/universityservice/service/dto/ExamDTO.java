package ir.mahdi.universityservice.service.dto;

import ir.mahdi.universityservice.base.BaseDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class ExamDTO extends BaseDTO<Long> {
    private String title;
    private String description;
    private int duration;
}
