package com.example.Ecommerce.service;

import com.example.Ecommerce.Dto.UserDto;
import com.example.Ecommerce.Exception.UserError;
import com.example.Ecommerce.Exception.UserException;
import com.example.Ecommerce.model.Order;
import com.example.Ecommerce.model.Role;
import com.example.Ecommerce.model.User;
import com.example.Ecommerce.repository.OrderRepository;
import com.example.Ecommerce.repository.RoleRepository;
import com.example.Ecommerce.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Transactional
@Slf4j
@Service

// TODO: 04.12.2023
public class UserServiceImplementation implements UserService{
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    private final RoleRepository roleRepository;
    @Override
    public User saveUser(UserDto user) {
        User userToSave = new User();
        userToSave.setUsername(user.getUsername());
        userToSave.setPassword(user.getPassword());
        userToSave.setEmail(user.getEmail());
        userToSave.setCreated_at(LocalDateTime.now());
        userToSave.setEdited_at(LocalDateTime.now());
        Role role = roleRepository.findByName("ROLE_USER");
        ArrayList<Role> roles = new ArrayList<>();
        roles.add(role);
        userToSave.setRoles(roles);
        return userRepository.save(userToSave);
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String userName, String roleName) {
        User user = userRepository.findByUsername(userName).orElseThrow(
                () -> new UserException(UserError.USER_NOT_FOUND));
        Role role = roleRepository.findByName(roleName);
        user.getRoles().add(role);
    }

    @Override
    public User getUser(String username) {
        return userRepository.findByUsername(username)
             .orElseThrow(() -> new UserException(UserError.USER_NOT_FOUND));
    }

    @Override
    public List<Order> getUserOrderList(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserException(UserError.USER_NOT_FOUND));
        return orderRepository.findAllByUserId(user.getId());
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserException(UserError.USER_NOT_FOUND));
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserException(UserError.USER_NOT_FOUND));
    }

    @Override
    public User getUserByEmailOrUsername(String email, String username) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserException(UserError.USER_NOT_FOUND));
    }
}
