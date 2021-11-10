package ir.mahdi.universityservice.mapper;

import ir.mahdi.universityservice.domain.Course;
import ir.mahdi.universityservice.service.dto.CourseDTO;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class CourseMapperToCourseDTO {
    public Course convertDTOToCourse(CourseDTO courseDTO) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = format.parse(courseDTO.getStartDate());
        Date endDate = format.parse(courseDTO.getEndDate());
        return Course.builder()
                .title(courseDTO.getTitle())
                .startDate(startDate)
                .endDate(endDate).build();
    }
}
