package com.service;

import com.dao.RoleRepository;
import com.model.Role;
import com.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RoleServiceImpl implements RoleService{

    private RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl (RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }

    @Override
    public void addRole(Role role) {
        roleRepository.save(role);
    }

    @Override
    public void removeRoleById(long id) {
        roleRepository.deleteById(id);
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public void updateRole(Role role) {
        roleRepository.save(role);
    }

    @Override
    public Role getRole(long id) {
        return roleRepository.getById(id);
    }

    @Override
    public Role getRole(String name) {
        Role role = null;
        Optional<Role> optional = roleRepository.findByName(name);
        if (optional.isPresent()) {
            role = optional.get();
        }
        return role;
    }
}
