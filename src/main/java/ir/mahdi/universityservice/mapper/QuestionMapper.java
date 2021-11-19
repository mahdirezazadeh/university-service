package ir.mahdi.universityservice.mapper;

import ir.mahdi.universityservice.base.mapper.BaseMapper;
import ir.mahdi.universityservice.domain.base.Question;
import ir.mahdi.universityservice.service.dto.QuestionDTO;
import org.mapstruct.Mapper;

@Mapper
public interface QuestionMapper extends BaseMapper<Question, QuestionDTO> {
}
