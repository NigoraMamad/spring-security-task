package edu.epam.fop.web.service;

import edu.epam.fop.web.dto.UserDTO;
import edu.epam.fop.web.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUsers();
    void createUser(UserDTO dto);
    void deleteUserById(Long id);
    Optional<User> findByUsername(String username);
    Optional<User> findById(Long id);
    User save(User user);
    List<User> getAllStudents();
}
