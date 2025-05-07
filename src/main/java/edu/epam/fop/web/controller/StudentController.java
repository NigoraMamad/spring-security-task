package edu.epam.fop.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
public class StudentController {

    @GetMapping
    public ResponseEntity<String> getAllStudents() {
        return ResponseEntity.ok("List of students (placeholder)");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateStudent(@PathVariable("id") Long id, @RequestBody String body) {
        return ResponseEntity.ok("Updated student with ID: " + id);
    }
}