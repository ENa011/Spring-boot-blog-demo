package com.cogent.blog.spring.demo.service;

import com.cogent.blog.spring.demo.entity.Post;

import java.util.*;

public interface PostService {
    List<Post> FindAllPost();
    Post FindPostById(long post_id);
    Post CreatePost(Post post);
    Post UpdatePost(Long post_id, Post updatePost);
    void deletePost(long post_id);

}
