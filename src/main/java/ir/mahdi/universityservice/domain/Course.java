package ir.mahdi.universityservice.domain;

import ir.mahdi.universityservice.base.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = Course.TABLE_NAME)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Course extends BaseEntity<Long> {
    static final String TABLE_NAME = "course_table";
    private static final String TITLE = "title";
    private static final String START_DATE = "start_date";
    private static final String END_DATE = "end_date";

    @Column(name = TITLE)
    private String title;

    @Column(name = START_DATE)
    private Date startDate;

    @Column(name = END_DATE)
    private Date endDate;

    @ManyToOne
    private Teacher teacher;

    @ElementCollection
    @Builder.Default
    private Set<Long> studentIds = new HashSet<>();
}
