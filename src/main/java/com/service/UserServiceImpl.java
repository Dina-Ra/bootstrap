package com.service;

import com.dao.UserDao;
import com.model.Role;
import com.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class UserServiceImpl implements UserService{

    private UserDao daoHibernate;

    @Autowired
    public UserServiceImpl(UserDao daoHibernate) {
        this.daoHibernate = daoHibernate;
    }

    @Transactional
    @Override
    public void addUser(User user, String[] roles) {
        user.setRoles(getSetRoles(roles));
        daoHibernate.addUser(user);
    }

    @Override
    public void removeUserById(long id) {
        daoHibernate.removeUserById(id);
    }

    @Transactional
    @Override
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        userList = daoHibernate.getAllUsers();
        return userList;
    }

    @Transactional
    @Override
    public void updateUser(User user, long id, String[] roles) {
        user.setRoles(getSetRoles(roles));
        daoHibernate.updateUser(user, id);
    }

    @Transactional
    @Override
    public User getUser(long id){
        return daoHibernate.getUser(id);
    }

    @Transactional
    @Override
    public User getUser(String name) {
        return daoHibernate.getUser(name);
    }

    private Set<Role> getSetRoles (String[] roles) {
        Set<Role> setRoles = new HashSet<>();
        for (String role : roles) {
            setRoles.add(new Role((role.equals("ADMIN") ? 1L : 2l), "ROLE_" + role));
        }
        return setRoles;
    }
}
