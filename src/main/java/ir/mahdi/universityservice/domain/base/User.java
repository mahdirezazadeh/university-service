package ir.mahdi.universityservice.domain.base;


import ir.mahdi.universityservice.base.BaseEntity;
import ir.mahdi.universityservice.domain.Role;
import ir.mahdi.universityservice.domain.enumeration.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Table(name = User.TABLE_NAME)
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = User.USER_TYPE)
public class User extends BaseEntity<Long> {
    static final String TABLE_NAME = "user_table";
    static final String USER_TYPE = "user_type";
    private static final String GENDER = "gender";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String FIRST_NAME = "first_name";
    private static final String LASTNAME = "lastname";
    private static final String BIRTHDAY = "birthday";
    private static final String NATIONAL_CODE = "national_code";
    private static final String EMAIL = "email";
    private static final String PHONE_NUMBER = "phone_number";
    private static final String IS_ACTIVE = "is_active";
    private static final String IS_CONFIRMED = "is_confirmed";

    @Column(name = USERNAME, unique = true, nullable = false)
    @NotNull
    @NotEmpty(message = "Username can not be empty!")
    @NotBlank
    private String username;

    @NotNull
    @NotEmpty(message = "Username can not be empty!")
    @NotBlank
    @Column(name = PASSWORD, nullable = false)
    private String password;

    @Column(name = FIRST_NAME, nullable = false)
    private String firstName;

    @Column(name = LASTNAME, nullable = false)
    private String lastName;

    @Column(name = BIRTHDAY)
    private Date birthday;

    @Column(name = NATIONAL_CODE)
    private String nationalCode;

    @NotNull
    @NotEmpty(message = "Username can not be empty!")
    @NotBlank
    @Column(name = EMAIL, unique = true, nullable = false)
    private String email;

    @Column(name = PHONE_NUMBER, unique = true, nullable = false)
    private String phoneNumber;

    @Column(name = GENDER)
    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    @Column(name = IS_ACTIVE)
    private boolean isActive = true;

    @Column(name = IS_CONFIRMED)
    private boolean isConfirmed = false;

    @ManyToMany
    private Set<Role> roles = new HashSet<>();

    public String getFullName() {
        return firstName.concat(" ").concat(lastName);
    }
}
