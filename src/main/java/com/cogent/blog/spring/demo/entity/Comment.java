package com.cogent.blog.spring.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Table(
        name="comments"
)
@Entity
public class Comment {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private long id;

    @NotEmpty(message = "name can't be empty")
    @Column(unique = true, nullable = false)
    private String name;

    @NotEmpty(message = "email can't be empty")
    @Email
    @Column(unique = true, nullable = false)
    private String email;


    private String body;

    @ManyToOne(
            fetch= FetchType.LAZY
    )
    @JoinColumn(
            name="post_id",
            nullable = false
    )
    @JsonIgnore
    private Post post;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
