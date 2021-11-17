package ir.mahdi.universityservice.domain;

import ir.mahdi.universityservice.domain.base.Question;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MultipleChoiceQuestion extends Question<String, String> {

    @ElementCollection
    private Set<String> choices = new HashSet<>();
}
