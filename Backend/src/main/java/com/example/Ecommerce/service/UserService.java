package com.example.Ecommerce.service;

import com.example.Ecommerce.model.Role;
import com.example.Ecommerce.model.User;
import com.example.Ecommerce.Dto.UserDto;
public interface UserService {

    User saveUser(UserDto user);

    Role saveRole(Role role);

    void addRoleToUser(String userName, String roleName);

    User getUser(String username);
}
