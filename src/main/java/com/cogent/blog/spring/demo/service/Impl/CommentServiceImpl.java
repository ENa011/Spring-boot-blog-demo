package com.cogent.blog.spring.demo.service.Impl;

import com.cogent.blog.spring.demo.entity.Comment;
import com.cogent.blog.spring.demo.entity.Post;
import com.cogent.blog.spring.demo.exception.BlogApiException;
import com.cogent.blog.spring.demo.exception.ResourceNotFoundException;
import com.cogent.blog.spring.demo.repository.CommentRepository;
import com.cogent.blog.spring.demo.repository.PostRepository;
import com.cogent.blog.spring.demo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    PostRepository postRepository;

    @Override
    public List<Comment> getCommentsByPostId(Long postId) {
        List<Comment> comments = commentRepository.findByPostId(postId);
        return comments;
    }

    @Override
    public Comment getCommentById(Long postId, Long commentId) {
        Post post = postRepository.findById(postId).
                orElseThrow(()-> new ResourceNotFoundException("post", "post Id", postId));

        Comment comment = commentRepository.findById(commentId).
                orElseThrow(()-> new ResourceNotFoundException("comment", "comment Id", commentId));

        if(comment.getPost().getId() != post.getId()) {
            throw new BlogApiException(HttpStatus.BAD_REQUEST , "comment does not belong to post");
        }

        return comment;
    }

    @Override
    public Comment createComment(Long postId, Comment comment) {
        Post post = postRepository.findById(postId)
                .orElseThrow(()->new ResourceNotFoundException("post", "post Id", postId));
        comment.setPost(post);
        return commentRepository.save(comment);
    }

    @Override
    public Comment updateComment(Long postId, Long commentId, Comment comment) {
        Post post = postRepository.findById(postId).
                orElseThrow(()-> new ResourceNotFoundException("post", "post Id", postId));

        Comment newComment = commentRepository.findById(commentId).
                orElseThrow(()-> new ResourceNotFoundException("comment", "comment Id", commentId));

        if(newComment.getPost().getId() != post.getId()) {
            throw new BlogApiException(HttpStatus.BAD_REQUEST , "comment does not belong to post");
        }

        newComment.setName(comment.getName());
        newComment.setBody(comment.getBody());
        newComment.setEmail(comment.getEmail());
        return commentRepository.save(newComment);
    }

    @Override
    public void deleteComment(Long postId, Long commentId) {
        Post post = postRepository.findById(postId).
                orElseThrow(()-> new ResourceNotFoundException("post", "post Id", postId));

        Comment newComment = commentRepository.findById(commentId).
                orElseThrow(()-> new ResourceNotFoundException("comment", "comment Id", commentId));

        if(newComment.getPost().getId() != post.getId()) {
            throw new BlogApiException(HttpStatus.BAD_REQUEST , "comment does not belong to post");
        }

        commentRepository.deleteById(commentId);
    }
}
