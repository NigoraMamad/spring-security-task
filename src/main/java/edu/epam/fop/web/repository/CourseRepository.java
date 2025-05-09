package edu.epam.fop.web.repository;

import edu.epam.fop.web.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
