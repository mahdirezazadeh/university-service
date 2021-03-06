package ir.mahdi.universityservice.domain;

import ir.mahdi.universityservice.domain.base.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


@Entity
//@Table(name = Admin.TABLE_NAME)
@Getter
@Setter
@Builder
@DiscriminatorValue(value = Admin.DISCRIMINATOR_VALUE)
public class Admin extends User {
    static final String TABLE_NAME = "admin_table";
    static final String DISCRIMINATOR_VALUE = "ADMIN";


    public Admin() {
        this.setUserType("admin");
    }
}

