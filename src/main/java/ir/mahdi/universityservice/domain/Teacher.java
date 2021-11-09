package ir.mahdi.universityservice.domain;

import ir.mahdi.universityservice.domain.base.User;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


@Entity
//@Table(name = Teacher.TABLE_NAME)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@DiscriminatorValue(value = Teacher.DISCRIMINATOR_VALUE)
public class Teacher extends User {
    static final String TABLE_NAME = "teacher_table";
    static final String DISCRIMINATOR_VALUE = "TEACHER";
    private static final String TEACHER_CODE = "teacher_code";

    @Column(name = TEACHER_CODE)
    private String teacherCode;
}