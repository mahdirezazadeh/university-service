package ir.mahdi.universityservice.mapper;

import ir.mahdi.universityservice.base.mapper.BaseMapper;
import ir.mahdi.universityservice.domain.Exam;
import ir.mahdi.universityservice.service.dto.ExamDTO;
import org.mapstruct.Mapper;

@Mapper
public interface ExamMapper extends BaseMapper<Exam, ExamDTO> {
}
