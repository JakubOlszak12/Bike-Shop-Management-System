package com.example.Ecommerce.service;

import com.example.Ecommerce.model.Role;

public interface RoleService {
    Role saveRole(Role role);
    Role getRole(Long id);
}
