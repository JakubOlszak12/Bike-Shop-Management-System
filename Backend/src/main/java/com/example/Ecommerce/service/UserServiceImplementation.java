package com.example.Ecommerce.service;

import com.example.Ecommerce.Exception.UserError;
import com.example.Ecommerce.Exception.UserException;
import com.example.Ecommerce.model.Role;
import com.example.Ecommerce.model.User;
import com.example.Ecommerce.repository.RoleRepository;
import com.example.Ecommerce.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Transactional
@Slf4j
@Service

// TODO: 04.12.2023
public class UserServiceImplementation implements UserService{
    private final UserRepository userRepository;

    private final RoleRepository roleRepository;
    @Override
    public User saveUser(User user) {
        return null;
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String userName, String roleName) {

    }

    @Override
    public User getUser(String username) {
        return null;
    }


//    @Override
//    public void addRoleToUser(String userName, String roleName) {
//        User user = userRepository.findByUsername(userName).orElseThrow(
//                () -> new UserException(UserError.USER_NOT_FOUND));
//        Role role = roleRepository.findByName(roleName);
//        user.setRole(role);
//    }
//
//    @Override
//    public User getUser(String username) {
//        return userRepository.findByUsername(username)
//                .orElseThrow(() -> new UserException(UserError.USER_NOT_FOUND));
//    }
}
