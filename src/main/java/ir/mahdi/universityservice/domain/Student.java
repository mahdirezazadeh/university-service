package ir.mahdi.universityservice.domain;

import ir.mahdi.universityservice.domain.base.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;


@Entity
//@Table(name = Student.TABLE_NAME)
@Getter
@Setter
@AllArgsConstructor
@Builder
@DiscriminatorValue(value = Student.DISCRIMINATOR_VALUE)
public class Student extends User {
    static final String TABLE_NAME = "student_table";
    static final String DISCRIMINATOR_VALUE = "STUDENT";
    private static final String STUDENT_CODE = "student_code";

    @Column(name = STUDENT_CODE)
    private String studentCode;

    @ManyToMany
    @Builder.Default
    private Set<Course> courses = new HashSet<>();

    public Student() {
        setUserType("Student");
    }
}
