package ir.mahdi.universityservice.mapper;

import ir.mahdi.universityservice.base.mapper.BaseMapper;
import ir.mahdi.universityservice.domain.Course;
import ir.mahdi.universityservice.service.dto.CourseDTODate;
import org.mapstruct.Mapper;

@Mapper
public interface CourseMapper extends BaseMapper<Course, CourseDTODate> {
}
