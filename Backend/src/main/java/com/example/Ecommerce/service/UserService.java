package com.example.Ecommerce.service;

import com.example.Ecommerce.model.Role;
import com.example.Ecommerce.model.User;

public interface UserService {

    User saveUser(User user);

    Role saveRole(Role role);

    void addRoleToUser(String userName, String roleName);

    User getUser(String username);
}
