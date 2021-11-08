package ir.mahdi.universityservice.repository;

import ir.mahdi.universityservice.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
