package com.example.Ecommerce.service;

import com.example.Ecommerce.model.Role;
import com.example.Ecommerce.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RoleServiceImplementation implements RoleService {
    RoleRepository roleRepository;
    @Override
    public Role saveRole(Role role){
        return roleRepository.save(role);
    }

    @Override
    public Role getRole(Long id){
        return roleRepository.findById(id).get();
    }
}
