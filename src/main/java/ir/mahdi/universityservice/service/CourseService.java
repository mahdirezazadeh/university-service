package ir.mahdi.universityservice.service;

import ir.mahdi.universityservice.base.service.BaseService;
import ir.mahdi.universityservice.domain.Course;
import ir.mahdi.universityservice.domain.Teacher;

import java.util.List;

public interface CourseService extends BaseService<Course, Long> {
    public List<Course> findCoursesByTeacher(Teacher teacher);
}
