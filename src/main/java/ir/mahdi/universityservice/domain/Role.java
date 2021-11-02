package ir.mahdi.universityservice.domain;

import ir.mahdi.universityservice.base.BaseEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = Role.TABLE_NAME)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Role extends BaseEntity<Integer> {
    static final String TABLE_NAME = "role_table";
    private static final String NAME = "name";

    @Column(name = NAME)
    private String name;

    @ManyToMany
    @Builder.Default
    private Set<Operation> operations = new HashSet<>();

}
