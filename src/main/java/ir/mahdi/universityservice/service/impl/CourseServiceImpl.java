package ir.mahdi.universityservice.service.impl;

import ir.mahdi.universityservice.base.service.impl.BaseServiceImpl;
import ir.mahdi.universityservice.domain.Course;
import ir.mahdi.universityservice.repository.CourseRepository;
import ir.mahdi.universityservice.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;


@Service
@Transactional(readOnly = true)
public class CourseServiceImpl extends BaseServiceImpl<Course, Long, CourseRepository> implements CourseService {

    @Autowired
    public CourseServiceImpl(CourseRepository repository) {
        super(repository);
    }

    @Override
    @Transactional
    public Course save(Course admin) {
        return super.save(admin);
    }


    @Override
    @Transactional
    public List<Course> saveAll(Collection<Course> admins) {
        return super.saveAll(admins);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        super.deleteById(id);
    }


}
