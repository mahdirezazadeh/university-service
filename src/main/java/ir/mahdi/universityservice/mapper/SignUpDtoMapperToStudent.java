package ir.mahdi.universityservice.mapper;

import ir.mahdi.universityservice.base.mapper.BaseMapper;
import ir.mahdi.universityservice.domain.Student;
import ir.mahdi.universityservice.service.dto.SignUpDto;
import org.mapstruct.Mapper;

@Mapper
public interface SignUpDtoMapperToStudent extends BaseMapper<Student, SignUpDto> {
}
