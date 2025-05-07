package edu.epam.fop.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @GetMapping
    public ResponseEntity<String> getAllCourses() {
        return ResponseEntity.ok("List of all courses (public endpoint)");
    }

    @PostMapping
    public ResponseEntity<String> createCourse(@RequestBody String course) {
        return ResponseEntity.ok("Created course: " + course);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCourse(@PathVariable("id") Long id, @RequestBody String course) {
        return ResponseEntity.ok("Updated course with ID: " + id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable("id") Long id) {
        return ResponseEntity.ok("Deleted course with ID: " + id);
    }
}

