package ir.mahdi.universityservice.domain;

import ir.mahdi.universityservice.base.BaseEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Exam extends BaseEntity<Long> {
//    private final String TITLE = "title";
//    private final String DESCRIPTION = "description";
//    private final String DURATION = "duration";


    //    @Column(name = TITLE)
    private String title;

    //    @Column(name = DESCRIPTION)
    private String description;

    //    @Column(name = DURATION)
    private int duration = 0;

    @ManyToOne
    private Course course;
}
