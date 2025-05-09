package edu.epam.fop.web.config;

import edu.epam.fop.web.entity.Role;
import edu.epam.fop.web.entity.User;
import edu.epam.fop.web.repository.RoleRepository;
import edu.epam.fop.web.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.DependsOn;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;

@Component
@DependsOn("roleSeeder") // Ensures RoleSeeder runs before this
public class UserSeeder {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserSeeder(UserRepository userRepository,
                      RoleRepository roleRepository,
                      PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void seedUsers() {
        createIfNotExists("admin", "admin123", "ADMIN");
        createIfNotExists("professor", "prof123", "PROFESSOR");
        createIfNotExists("student", "stud123", "STUDENT");
    }

    private void createIfNotExists(String username, String rawPassword, String roleName) {
        if (userRepository.findByUsername(username).isPresent()) {
            return; // User already exists
        }

        Optional<Role> roleOpt = roleRepository.findByName(roleName);
        if (roleOpt.isEmpty()) {
            System.err.println("⚠️ Cannot create user '" + username + "' — missing role: " + roleName);
            return;
        }

        Role role = roleOpt.get();
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(rawPassword));
        user.setRoles(Set.of(role));

        userRepository.save(user);
        System.out.println("✅ Demo user created: " + username + " with role " + roleName);
    }
}