package com.cogent.blog.spring.demo.repository;

import com.cogent.blog.spring.demo.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
