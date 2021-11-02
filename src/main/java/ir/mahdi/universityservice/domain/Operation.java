package ir.mahdi.universityservice.domain;

import ir.mahdi.universityservice.base.BaseEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = Operation.TABLE_NAME)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Operation extends BaseEntity<Integer> {
    static final String TABLE_NAME = "operation_table";
    private static final String NAME = "name";

    @Column(name = NAME)
    private String name;
}
