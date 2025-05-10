package edu.epam.fop.web.config;

import edu.epam.fop.web.dto.UserDTO;
import edu.epam.fop.web.entity.Role;
import edu.epam.fop.web.entity.User;
import edu.epam.fop.web.repository.RoleRepository;
import edu.epam.fop.web.repository.UserRepository;
import edu.epam.fop.web.service.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component
public class UserSeeder {

    private final UserRepository userRepository;
    private final UserService userService;

    public UserSeeder(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @PostConstruct
    public void seedUsers() {
        List<UserDTO> demoUsers = List.of(
                new UserDTO("admin", "admin123", Set.of("ADMIN")),
                new UserDTO("professor", "prof123", Set.of("PROFESSOR")),
                new UserDTO("student", "stud123", Set.of("STUDENT"))
        );

        for (UserDTO dto : demoUsers) {
            if (userRepository.findByUsername(dto.getUsername()).isEmpty()) {
                userService.createUser(dto);
                System.out.println("Seeded user: " + dto.getUsername());
            }
        }
    }
}