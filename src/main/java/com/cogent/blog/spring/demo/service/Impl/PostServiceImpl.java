package com.cogent.blog.spring.demo.service.Impl;

import com.cogent.blog.spring.demo.entity.Post;
import com.cogent.blog.spring.demo.exception.ResourceNotFoundException;
import com.cogent.blog.spring.demo.repository.PostRepository;
import com.cogent.blog.spring.demo.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    PostRepository postRepository;

    @Override
    public List<Post> FindAllPost() {
        return postRepository.findAll();
    }

    @Override
    public Post FindPostById(long post_id) {
        Post post = postRepository.findById(post_id)
                .orElseThrow(()->new ResourceNotFoundException("post", "post Id", post_id));
        return post;
    }

    @Override
    public Post CreatePost(Post post) {
        return postRepository.save(post);
    }

    @Override
    public Post UpdatePost(Long post_id, Post updatePost) {
        Post post = postRepository.findById(post_id)
                .orElseThrow(()->new ResourceNotFoundException("post", "post Id", post_id));
        post.setTitle(updatePost.getTitle());
        post.setDescription(updatePost.getDescription());
        post.setContent(updatePost.getContent());
        return postRepository.save(post);
    }

    @Override
    public void deletePost(long post_id) {
        Post post = postRepository.findById(post_id)
                .orElseThrow(()->new ResourceNotFoundException("post", "post Id", post_id));
        postRepository.deleteById(post_id);
    }
}
