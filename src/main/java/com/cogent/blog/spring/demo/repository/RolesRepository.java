package com.cogent.blog.spring.demo.repository;

import com.cogent.blog.spring.demo.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolesRepository extends JpaRepository<Roles, Long> {
    Optional<Roles> findByRole(String role);
}
