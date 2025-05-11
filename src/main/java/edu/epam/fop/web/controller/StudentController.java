package edu.epam.fop.web.controller;

import edu.epam.fop.web.entity.User;
import edu.epam.fop.web.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {

    private final UserService userService;

    public StudentController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String viewStudents(Model model) {
        List<User> students = userService.getAllStudents();
        model.addAttribute("students", students);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("username", auth.getName());
        model.addAttribute("role", auth.getAuthorities().stream()
                .map(a -> a.getAuthority().replace("ROLE_", "").toLowerCase())
                .findFirst().orElse("user"));

        return "student_list";
    }

    @GetMapping("/edit/{id}")
    public String editStudentForm(@PathVariable Long id, Model model) {
        User student = userService.findById(id).orElseThrow();
        model.addAttribute("student", student);
        return "student_edit_form";
    }

    @PostMapping("/update/{id}")
    public String updateStudent(@PathVariable Long id, @ModelAttribute("student") User updatedUser) {
        User existing = userService.findById(id).orElseThrow();
        existing.setUsername(updatedUser.getUsername());
        userService.save(existing);
        return "redirect:/students";
    }
}