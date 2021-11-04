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
//@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpDto extends BaseDTO<Long> {
    private String username;
    private String password;
    private String password2;
    private String firstName;
    private String lastName;
    private Date birthday;
    private String nationalCode;
    private String email;
    private String phoneNumber;
    private Gender gender;
}
