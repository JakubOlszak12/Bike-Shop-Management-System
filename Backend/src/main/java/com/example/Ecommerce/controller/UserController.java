package com.example.Ecommerce.controller;

import com.example.Ecommerce.Dto.UserDto;

import com.example.Ecommerce.model.User;
import com.example.Ecommerce.service.JwtUserDetailsService;

import com.example.Ecommerce.service.UserServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Autowired
    private UserServiceImplementation userService;

    @GetMapping("/orders")
    public ResponseEntity<?> getOrders(@RequestBody Long id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(userService.getUserOrderList(user.getUsername()));
    }
}
