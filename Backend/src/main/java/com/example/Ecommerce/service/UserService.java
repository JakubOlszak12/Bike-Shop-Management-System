package com.example.Ecommerce.service;

import com.example.Ecommerce.model.Order;
import com.example.Ecommerce.model.Role;
import com.example.Ecommerce.model.User;
import com.example.Ecommerce.Dto.UserDto;

import java.util.List;

public interface UserService {

    User saveUser(UserDto user);

    Role saveRole(Role role);

    void addRoleToUser(String userName, String roleName);

    User getUser(String username);

    List<Order> getUserOrderList(String username);

    User getUserById(Long id);

    User getUserByEmail(String email);

    User getUserByEmailOrUsername(String email, String username);
}
