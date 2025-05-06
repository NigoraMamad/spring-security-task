package edu.epam.fop.web.service;

import edu.epam.fop.web.entity.Role;
import java.util.*;

public interface RoleService {
    Role getRoleByName(String name);
    List<Role> getAllRoles();
}
