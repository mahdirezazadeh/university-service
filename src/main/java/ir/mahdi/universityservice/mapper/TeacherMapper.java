package ir.mahdi.universityservice.mapper;

import ir.mahdi.universityservice.base.mapper.BaseMapper;
import ir.mahdi.universityservice.domain.Teacher;
import ir.mahdi.universityservice.service.dto.TeacherDTO;
import org.mapstruct.Mapper;

@Mapper
public interface TeacherMapper extends BaseMapper<Teacher, TeacherDTO> {
}
