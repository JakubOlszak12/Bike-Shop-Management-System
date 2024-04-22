package com.example.Ecommerce.controller;

import com.example.Ecommerce.Dto.UserDto;
import com.example.Ecommerce.model.User;
import com.example.Ecommerce.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    UserService userService;

}
