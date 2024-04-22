package com.example.Ecommerce.controller;

import com.example.Ecommerce.Dto.UserDto;

import com.example.Ecommerce.service.JwtUserDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private JwtUserDetailsService userDetailsService;


}
