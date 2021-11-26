package ir.mahdi.universityservice.mapper;

import ir.mahdi.universityservice.domain.StudentExamAnswer;
import ir.mahdi.universityservice.service.dto.StudentExamAnswersDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StudentExamAnswerMapper {
    public StudentExamAnswersDTO convertStudentExamAnswerToDTO(StudentExamAnswer studentExamAnswer) {
        StudentExamAnswersDTO studentExamAnswersDTO = new StudentExamAnswersDTO();
        studentExamAnswersDTO.setStudentId(studentExamAnswer.getStudent().getId());
        studentExamAnswersDTO.setStudentUsername(studentExamAnswer.getStudent().getUsername());
        studentExamAnswersDTO.setStudentFullName(studentExamAnswer.getStudent().getFullName());
        studentExamAnswersDTO.setExamAnswerId(studentExamAnswer.getId());
        studentExamAnswersDTO.setExamId(studentExamAnswer.getExam().getId());
        return studentExamAnswersDTO;
    }

    public List<StudentExamAnswersDTO> convertListStudentExamAnswerToDTO(List<StudentExamAnswer> studentExamAnswers) {
        ArrayList<StudentExamAnswersDTO> studentExamAnswersDTOS = new ArrayList<>();
        for (StudentExamAnswer studentExamAnswer : studentExamAnswers
        ) {
            studentExamAnswersDTOS.add(convertStudentExamAnswerToDTO(studentExamAnswer));
        }
        return studentExamAnswersDTOS;
    }

}
