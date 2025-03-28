package com.cogent.blog.spring.demo.service;

import com.cogent.blog.spring.demo.entity.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> getCommentsByPostId(Long postId);
    Comment getCommentById(Long postId, Long commentId);
    Comment createComment(Long postId, Comment comment);
    Comment updateComment(Long postId, Long commentId, Comment comment);
    void deleteComment(Long postId, Long commentId);
}
