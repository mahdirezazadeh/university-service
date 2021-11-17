package ir.mahdi.universityservice.domain;

import ir.mahdi.universityservice.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Exam extends BaseEntity<Long> {
    private static final String TITLE = "title";
    private static final String DESCRIPTION = "description";
    private static final String DURATION = "duration";

    @Column(name = TITLE)
    private String title;

    @Column(name = DESCRIPTION)
    private String description;

    @Column(name = DURATION)
    private int duration = 0;

    @ManyToOne
    private Course course;
}
