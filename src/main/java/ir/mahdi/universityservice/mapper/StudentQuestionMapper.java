package ir.mahdi.universityservice.mapper;

import ir.mahdi.universityservice.domain.MultipleChoiceQuestion;
import ir.mahdi.universityservice.domain.StudentQuestionAnswer;
import ir.mahdi.universityservice.domain.base.Question;
import ir.mahdi.universityservice.service.dto.StudentQuestionDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StudentQuestionMapper {
    public StudentQuestionDTO convertQuestionToDTO(StudentQuestionAnswer studentQuestionAnswer) {
        Question<?, ?> question = studentQuestionAnswer.getExamQuestion().getQuestion();
        StudentQuestionDTO studentQuestionDTO =
                new StudentQuestionDTO(
                        studentQuestionAnswer.getExamQuestion().getScore(),
                        (String) question.getQuestion(),
                        studentQuestionAnswer.getAnswer());
        studentQuestionDTO.setId(studentQuestionAnswer.getId());
        studentQuestionDTO.setQuestionType(question.getQuestionType());

//        if question is multiple choice question map choices
        if (studentQuestionDTO.getQuestionType().equals(MultipleChoiceQuestion.getQuestionTypeString())) {
            MultipleChoiceQuestion multipleChoiceQuestion = (MultipleChoiceQuestion) question;
            for (String choice : multipleChoiceQuestion.getChoices()) {
                studentQuestionDTO.getChoices().add(choice);
            }
        }
        return studentQuestionDTO;
    }

    public List<StudentQuestionDTO> convertListQuestionToDTO(List<StudentQuestionAnswer> studentQuestionAnswers) {
        List<StudentQuestionDTO> studentQuestionDTOS = new ArrayList<>();
        for (StudentQuestionAnswer studentQuestionAnswer : studentQuestionAnswers
        ) {
            studentQuestionDTOS.add(convertQuestionToDTO(studentQuestionAnswer));
        }
        return studentQuestionDTOS;
    }
}
