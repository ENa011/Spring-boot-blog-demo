package com.cogent.blog.spring.demo.repository;

import com.cogent.blog.spring.demo.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.*;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPostId(Long postId);

//    @Query("Select* from comments where post_id = :postId and id = :commentId")
//    Comment findByIdAndPostID(@Param("postId") Long postId, @Param("commentId") Long commentId);
//
//    @Query("Delete from comments where post_Id =:postId and id = :commentId")
//    void  deleteByIdAndPostID(@Param("postID") Long postId, @Param("commentId") Long commentId);
}
