package ir.mahdi.universityservice.domain;

import ir.mahdi.universityservice.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Table(name = "StudentExamAnswer", uniqueConstraints = {
        @UniqueConstraint(name = "uc_student_exam_answer_exam_id", columnNames = {"exam_id", "student_id"})
})
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentExamAnswer extends BaseEntity<Long> {
    private static final String END_TIME = "end_time";
    @ManyToOne
    private Exam exam;

    @ManyToOne
    private Student student;

    private float examScore = -1;

    @Column(name = END_TIME)
    private LocalDateTime endTime;

    @OneToMany(cascade = CascadeType.ALL)
    private List<StudentQuestionAnswer> studentAnswers = new ArrayList<>();

    public void setEndTime(int durationInMinutes) {
        LocalDateTime createDate = getCreateDate();
        endTime = createDate.plusMinutes(durationInMinutes).plusSeconds(10);
    }
}
