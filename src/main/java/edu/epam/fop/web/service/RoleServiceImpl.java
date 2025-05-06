package edu.epam.fop.web.service;

import edu.epam.fop.web.entity.Role;
import edu.epam.fop.web.repository.RoleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepo;

    @Override
    public Role getRoleByName(String name) {
        return roleRepo.findByName(name)
                .orElseThrow(() -> new RuntimeException("Role not found"));
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepo.findAll();
    }
}
