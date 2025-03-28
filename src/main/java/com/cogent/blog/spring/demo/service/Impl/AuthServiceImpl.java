package com.cogent.blog.spring.demo.service.Impl;

import com.cogent.blog.spring.demo.entity.Roles;
import com.cogent.blog.spring.demo.entity.Users;
import com.cogent.blog.spring.demo.payload.LoginDto;
import com.cogent.blog.spring.demo.payload.RegisterDto;
import com.cogent.blog.spring.demo.repository.RolesRepository;
import com.cogent.blog.spring.demo.repository.UsersRepository;
import com.cogent.blog.spring.demo.security.JwtTokenProvider;
import com.cogent.blog.spring.demo.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    RolesRepository rolesRepository;

    @Autowired
     PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Override
    public String loginUser(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getUsernameOrEmail(), loginDto.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenProvider.generateToken(authentication);
        System.out.println(token);
        return token;
    }

    @Override
    public String registerUser(RegisterDto registerDto) {
        if(usersRepository.existsByUsername(registerDto.getUsername())){
            throw new RuntimeException();
        }
        if(usersRepository.existsByEmail(registerDto.getEmail())){
            throw new RuntimeException();
        }
        Users users = new Users();
        users.setName(registerDto.getName());
        users.setEmail(registerDto.getEmail());
        users.setUsername(registerDto.getUsername());

        users.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        Set<Roles> roles = new HashSet<>();
        Roles userRoles = rolesRepository.findByRole("ROLE_USER").get();
        roles.add(userRoles);
        users.setRoles(roles);
        usersRepository.save(users);
        return "User register Successfully";
    }
}
