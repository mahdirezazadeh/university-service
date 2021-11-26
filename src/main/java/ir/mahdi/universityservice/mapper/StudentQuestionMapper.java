package ir.mahdi.universityservice.mapper;

import ir.mahdi.universityservice.domain.ExamQuestion;
import ir.mahdi.universityservice.domain.MultipleChoiceQuestion;
import ir.mahdi.universityservice.domain.StudentQuestionAnswer;
import ir.mahdi.universityservice.domain.base.Question;
import ir.mahdi.universityservice.service.ExamQuestionService;
import ir.mahdi.universityservice.service.dto.StudentQuestionDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class StudentQuestionMapper {

    private ExamQuestionService examQuestionService;

    public StudentQuestionDTO convertQuestionToDTO(StudentQuestionAnswer studentQuestionAnswer) {
        ExamQuestion examQuestion = examQuestionService.findById(studentQuestionAnswer.getExamQuestionId()).get();
        Question<?, ?> question = examQuestion.getQuestion();
        StudentQuestionDTO studentQuestionDTO =
                new StudentQuestionDTO(
                        examQuestion.getScore(),
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

    public StudentQuestionDTO convertQuestionToDTOForTeacher(StudentQuestionAnswer studentQuestionAnswer) {
        ExamQuestion examQuestion = examQuestionService.findById(studentQuestionAnswer.getExamQuestionId()).get();
        Question<?, ?> question = examQuestion.getQuestion();
        StudentQuestionDTO studentQuestionDTO =
                new StudentQuestionDTO(
                        examQuestion.getScore(),
                        (String) question.getQuestion(),
                        studentQuestionAnswer.getAnswer());
        studentQuestionDTO.setId(studentQuestionAnswer.getId());
        studentQuestionDTO.setQuestionType(question.getQuestionType());
        studentQuestionDTO.setScore(studentQuestionAnswer.getScore());

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
            try {
                studentQuestionDTOS.add(convertQuestionToDTO(studentQuestionAnswer));
            } catch (Exception e) {

            }
        }
        return studentQuestionDTOS;
    }


    public List<StudentQuestionDTO> convertListQuestionToDTOForTeacher(List<StudentQuestionAnswer> studentQuestionAnswers) {
        List<StudentQuestionDTO> studentQuestionDTOS = new ArrayList<>();
        for (StudentQuestionAnswer studentQuestionAnswer : studentQuestionAnswers
        ) {
            try {
                studentQuestionDTOS.add(convertQuestionToDTOForTeacher(studentQuestionAnswer));
            } catch (Exception e) {

            }
        }
        return studentQuestionDTOS;
    }

    public float calculateExamScore(List<StudentQuestionDTO> studentQuestionDTOS) {
        float examScore = 0;
        for (StudentQuestionDTO studentQuestionDTO : studentQuestionDTOS
        ) {
            examScore += studentQuestionDTO.getScore();
        }
        return examScore;
    }
}
