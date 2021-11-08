package ir.mahdi.universityservice.service.dto;

import ir.mahdi.universityservice.base.BaseDTO;
import ir.mahdi.universityservice.domain.enumeration.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignUpDto extends BaseDTO<Long> {

    @NotNull
    @NotEmpty(message = "Username can not be empty!")
    @NotBlank
    private String username;

    @NotNull
    @NotEmpty(message = "Password can not be empty!")
    @NotBlank
    private String password;

//    @NotNull
//    @NotEmpty(message = "Password Confirmation can not be empty!")
//    @NotBlank
//    private String password2;

    @NotNull
    @NotEmpty(message = "Email can not be empty!")
    @NotBlank
    private String email;
    private String firstName;
    private String lastName;
    private Date birthday;
    private String nationalCode;
    private String phoneNumber;
    private Gender gender;
}
