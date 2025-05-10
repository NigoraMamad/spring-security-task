package edu.epam.fop.web.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
public class UserDTO {
    private String username;
    private String password;
    private Set<String> roles;

    public UserDTO(String username, String password, Set<String> roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }
}
