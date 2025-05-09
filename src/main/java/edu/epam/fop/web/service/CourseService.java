package edu.epam.fop.web.service;

import edu.epam.fop.web.entity.Course;

import java.util.List;
import java.util.Optional;

public interface CourseService {
    List<Course> findAll();
    Course save(Course course);
    Optional<Course> findById(Long id);
    void deleteById(Long id);
}
