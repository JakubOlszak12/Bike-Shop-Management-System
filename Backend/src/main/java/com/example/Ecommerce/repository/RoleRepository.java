package com.example.Ecommerce.repository;

import com.example.Ecommerce.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByName(String roleName);

}
