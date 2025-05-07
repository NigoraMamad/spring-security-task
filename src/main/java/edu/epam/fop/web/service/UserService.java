package edu.epam.fop.web.service;

import edu.epam.fop.web.dto.UserDTO;
import edu.epam.fop.web.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void createUser(UserDTO dto);
    Optional<User> findByUsername(String username);
    List<User> getAllUsers();
    void deleteUserById(Long id);
}
