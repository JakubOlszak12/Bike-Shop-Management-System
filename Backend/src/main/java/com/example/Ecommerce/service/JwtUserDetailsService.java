package com.example.Ecommerce.service;


import com.example.Ecommerce.Dto.UserDto;
import com.example.Ecommerce.Exception.UserError;
import com.example.Ecommerce.Exception.UserException;
import com.example.Ecommerce.model.Role;
import com.example.Ecommerce.model.User;
import com.example.Ecommerce.repository.RoleRepository;
import com.example.Ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userDao;
    @Autowired
    private PasswordEncoder bcryptEncoder;
    @Autowired
    private RoleRepository roleRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUsername(username).get();
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                getAuthorities(user.getRoles())
        );
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Collection<Role> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }
    public User save(UserDto user) {
        Optional<User> existingUser = userDao.findByEmailOrUsername(user.getEmail(), user.getUsername());
        if (existingUser.isPresent()) {
            if (existingUser.get().getEmail().equals(user.getEmail())) {
                throw new UserException(UserError.USER_EMAIL_NOT_AVAILABLE);
            } else {
                throw new UserException(UserError.USER_LOGIN_NOT_AVAILABLE);
            }
        }

        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
        newUser.setEmail(user.getEmail());
        LocalDateTime currentTime = LocalDateTime.now();
        newUser.setCreated_at(currentTime);
        newUser.setEdited_at(currentTime);
        Role role = roleRepository.findByName("ROLE_USER");
        newUser.getRoles().add(role);


        return userDao.save(newUser);
    }

}

