package edu.epam.fop.web.controller;

import edu.epam.fop.web.dto.UserDTO;
import edu.epam.fop.web.entity.User;
import edu.epam.fop.web.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public ResponseEntity<String> createUser(@RequestBody UserDTO dto) {
        userService.createUser(dto);
        return ResponseEntity.ok("User created");
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/users-ui")
    public String userForm(Model model) {
        model.addAttribute("userDTO", new UserDTO());
        model.addAttribute("users", userService.getAllUsers());
        return "admin_users";
    }

    @PostMapping("/users-ui")
    public String createUserWeb(@ModelAttribute UserDTO dto) {
        userService.createUser(dto);
        return "redirect:/admin/users-ui";
    }
}

