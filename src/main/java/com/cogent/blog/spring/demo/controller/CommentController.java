package com.cogent.blog.spring.demo.controller;

import com.cogent.blog.spring.demo.entity.Comment;
import com.cogent.blog.spring.demo.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;
import java.util.*;

@RestController
@RequestMapping("/api/posts")
public class CommentController {

    @Autowired
    CommentService commentService;

    @GetMapping("/{postId}/comments")
    public ResponseEntity<List<Comment>> getAllCommentByPostId(@PathVariable("postId") Long postId){
        var data = commentService.getCommentsByPostId(postId);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @GetMapping("/{postId}/comments/{commentId}")
    public ResponseEntity<Comment> getCommentByIdAndPostId(@PathVariable("postId") Long postId,
                                                           @PathVariable("commentId") Long commentId){
        var data = commentService.getCommentById(postId, commentId);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @PostMapping("/{postId}/comments")
    public ResponseEntity<Comment> createComment(@PathVariable("postId") Long postId,
                                                 @Valid @RequestBody Comment comment){
        var data = commentService.createComment(postId, comment);
        return new ResponseEntity<>(data, HttpStatus.CREATED);
    }

    @PutMapping("/{postId}/comments/{commentId}")
    public ResponseEntity<Comment> editComment(@PathVariable("postId") Long postId,
                                               @PathVariable("commentId") Long commentId,
                                               @Valid @RequestBody Comment comment){
        var data = commentService.updateComment(postId, commentId, comment);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @DeleteMapping("/{postId}/comments/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable("postId") Long postId,
                                                @PathVariable("commentId") Long commentId){
        commentService.deleteComment(postId, commentId);
        return new ResponseEntity<>("successfully deleted", HttpStatus.OK);
    }
}
