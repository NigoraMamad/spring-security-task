package edu.epam.fop.web.controller;

import edu.epam.fop.web.dto.UserDTO;
import edu.epam.fop.web.entity.User;
import edu.epam.fop.web.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
public class AdminController{
    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin/users")
    public String userListView(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "user_list";
    }

    @GetMapping("/admin/users/new")
    public String newUserForm(Model model) {
        model.addAttribute("user", new UserDTO());
        return "user_form";
    }

    @PostMapping("/admin/users")
    public String handleCreateUser(@ModelAttribute("user") UserDTO dto) {
        userService.createUser(dto);
        return "redirect:/admin/users";
    }

    @PostMapping("/admin/users/delete/{id}")
    public String deleteUserFromForm(@PathVariable("id") Long id, Principal principal) {
        User current = userService.findByUsername(principal.getName())
                .orElseThrow(() -> new IllegalStateException("User not found"));
        if (current.getId().equals(id)) {
            return "redirect:/admin/users?error=self-delete";
        }
        userService.deleteUserById(id);
        return "redirect:/admin/users";
    }
}


