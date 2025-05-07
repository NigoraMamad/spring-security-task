package edu.epam.fop.web.service;

import edu.epam.fop.web.dto.UserDTO;
import edu.epam.fop.web.entity.Role;
import edu.epam.fop.web.entity.User;
import edu.epam.fop.web.repository.RoleRepository;
import edu.epam.fop.web.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    
        @Autowired
        private UserRepository userRepo;

        @Autowired
        private RoleRepository roleRepo;

        @Autowired
        private PasswordEncoder passwordEncoder;

        @Override
        public void createUser(UserDTO dto) {
            User user = new User();
            user.setUsername(dto.getUsername());
           // hash the password using PasswordEncoder
            user.setPassword(passwordEncoder.encode(dto.getPassword()));
            //converting role names to Role entities
            Set<Role> roles = dto.getRoles().stream()
                    .map(roleName -> roleRepo.findByName(roleName)
                            .orElseThrow(() -> new RuntimeException("Role not found: " + roleName)))
                    .collect(Collectors.toSet());
            user.setRoles(roles);
            userRepo.save(user);
        }

        @Override
        public Optional<User> findByUsername(String username) {
            return userRepo.findByUsername(username);
        }

        @Override
        public List<User> getAllUsers() {
            return userRepo.findAll();
        }

    @Override
    public void deleteUserById(Long id) {
        if (!userRepo.existsById(id)) {
            throw new NoSuchElementException("User with ID " + id + " not found.");
        }
        userRepo.deleteById(id);
    }
    }

