package ir.mahdi.universityservice.controller;

import ir.mahdi.universityservice.domain.Teacher;
import ir.mahdi.universityservice.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TeacherRestController {

    private final TeacherService teacherService;

    @PreAuthorize("hasRole('admin')")
    @GetMapping("/teachers-list")
    public List<Teacher> getAvailableTeachers() {
        List<Teacher> teachers = teacherService.findAvailableTeachers();
        return teachers;
    }
}
