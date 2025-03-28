package com.cogent.blog.spring.demo.service;

import com.cogent.blog.spring.demo.entity.Users;
import com.cogent.blog.spring.demo.payload.LoginDto;
import com.cogent.blog.spring.demo.payload.RegisterDto;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface AuthService {
    String loginUser(LoginDto loginDto);
    String registerUser(RegisterDto registerDto);

}
