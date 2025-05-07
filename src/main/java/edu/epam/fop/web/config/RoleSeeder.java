package edu.epam.fop.web.config;

import edu.epam.fop.web.entity.Role;
import edu.epam.fop.web.repository.RoleRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RoleSeeder {

    private final RoleRepository roleRepository;

    public RoleSeeder(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @PostConstruct
    public void seedRoles() {
        List<String> roleNames = List.of("ADMIN", "PROFESSOR", "STUDENT");

        for (String roleName : roleNames) {
            if (roleRepository.findByName(roleName).isEmpty()) {
                Role role = new Role();
                role.setName(roleName);
                roleRepository.save(role);
                System.out.println("Seeded role: " + roleName);
            }
        }
    }
}

