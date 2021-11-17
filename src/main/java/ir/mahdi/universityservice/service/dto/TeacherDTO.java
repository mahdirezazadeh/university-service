package ir.mahdi.universityservice.service.dto;

import ir.mahdi.universityservice.base.BaseDTO;
import ir.mahdi.universityservice.domain.enumeration.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TeacherDTO extends BaseDTO<Long> {
    private String teacherCode;
    private String username;
    private String firstName;
    private String lastName;
    private Date birthday;
    private String nationalCode;
    private String email;
    private String phoneNumber;
    private Gender gender;
}
