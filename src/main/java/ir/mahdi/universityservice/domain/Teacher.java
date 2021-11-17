package ir.mahdi.universityservice.domain;

import ir.mahdi.universityservice.domain.base.User;
import ir.mahdi.universityservice.domain.enumeration.Gender;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;


@Entity
@Getter
@Setter
@DiscriminatorValue(value = Teacher.DISCRIMINATOR_VALUE)
public class Teacher extends User {
    static final String DISCRIMINATOR_VALUE = "TEACHER";
    private static final String TEACHER_CODE = "teacher_code";

    @Column(name = TEACHER_CODE)
    private String teacherCode;

    @Builder
    public Teacher(String userType, @NotNull @NotEmpty(message = "Username can not be empty!") @NotBlank String username, @NotNull @NotEmpty(message = "Username can not be empty!") @NotBlank String password, String firstName, String lastName, Date birthday, String nationalCode, @NotNull @NotEmpty(message = "Username can not be empty!") @NotBlank String email, @NotNull @NotEmpty(message = "Phone Number can not be empty!") @NotBlank String phoneNumber, Gender gender, boolean isActive, boolean isConfirmed, Set<Role> roles, String teacherCode) {
        super(userType, username, password, firstName, lastName, birthday, nationalCode, email, phoneNumber, gender, isActive, isConfirmed, roles);
        this.teacherCode = teacherCode;
    }

    public Teacher(String teacherCode) {
        this.teacherCode = teacherCode;
    }

    public Teacher() {
        setUserType("Teacher");
    }
}
