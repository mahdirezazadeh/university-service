package ir.mahdi.universityservice.controller.mapper;

import ir.mahdi.universityservice.base.mapper.BaseMapper;
import ir.mahdi.universityservice.domain.Student;
import ir.mahdi.universityservice.domain.base.User;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UserToStudent extends BaseMapper<Student, User> {
}
