package com.cogent.blog.spring.demo.security;

import com.cogent.blog.spring.demo.entity.Users;
import com.cogent.blog.spring.demo.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    UsersRepository usersRepository;


    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        Users users = usersRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow(()-> new RuntimeException());

        Set<GrantedAuthority> authorities = users
                .getRoles()
                .stream()
                .map(roles-> new SimpleGrantedAuthority(roles.getRole())).collect(Collectors.toSet());
        return new org.springframework.security.core.userdetails.User(
                users.getEmail(),
                users.getPassword(),
                authorities
        );
    }
}
