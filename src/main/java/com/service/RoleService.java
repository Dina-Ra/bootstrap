package com.service;

import com.model.Role;
import com.model.User;

import java.util.List;

public interface RoleService {

    public void addRole(Role role);

    public void removeRoleById(long id);

    public List<Role> getAllRoles();

    public void updateRole(Role role);

    public Role getRole(long id);

    public Role getRole(String name);

}
