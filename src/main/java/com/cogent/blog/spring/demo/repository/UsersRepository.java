package com.cogent.blog.spring.demo.repository;

import com.cogent.blog.spring.demo.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByUsername(String username);
    Optional<Users> findByEmail(String email);
    Optional<Users> findByUsernameOrEmail(String username, String Email);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);

}
