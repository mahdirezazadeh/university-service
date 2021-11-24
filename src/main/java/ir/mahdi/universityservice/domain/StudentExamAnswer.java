package ir.mahdi.universityservice.domain;

import ir.mahdi.universityservice.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Table(name = "StudentExamAnswer", uniqueConstraints = {
        @UniqueConstraint(name = "uc_student_exam_answer_exam_id", columnNames = {"exam_id", "student_id"})
})
@Entity
@Getter
@Setter
public class StudentExamAnswer extends BaseEntity<Long> {
    @ManyToOne
    private Exam exam;

    @ManyToOne
    private Student student;

    @OneToMany
    private List<StudentQuestionAnswer> studentAnswers;
}
