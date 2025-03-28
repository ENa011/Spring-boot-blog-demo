package com.cogent.blog.spring.demo.controller;

import com.cogent.blog.spring.demo.entity.Post;
import com.cogent.blog.spring.demo.service.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    PostService postService;

    @GetMapping
    public ResponseEntity<List<Post>> getAllPost(){
        var data = postService.FindAllPost();
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable("id") long id){
        var data = postService.FindPostById(id);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Post> createPost(@Valid @RequestBody Post post){
        var data = postService.CreatePost(post);
        return new ResponseEntity<>(data, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable("id") long id,
                                           @Valid @RequestBody Post post){
        var data = postService.UpdatePost(id, post);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable("id") long id){
        postService.deletePost(id);
        return new ResponseEntity<>("Successfully deleted", HttpStatus.OK);

    }

}
