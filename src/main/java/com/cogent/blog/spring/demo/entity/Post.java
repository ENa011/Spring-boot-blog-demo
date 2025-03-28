package com.cogent.blog.spring.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Table(
    name="posts",
        uniqueConstraints = {
            @UniqueConstraint(
                    columnNames = {"title"}
            )
        }
)
@Entity
public class Post {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private long id;

    @NotEmpty(message = "Title should not be empty")
    @Size(min =2, message = "title needs to be at least 2 characters")
    @Column(name="title", nullable = false)
    private String title;

    @NotEmpty(message = "description shouldn't be empty")
    @Size(min=2, message = "description has to be at least 2 characters")
    @Column(name="description", nullable = false)
    private String description;

    @NotEmpty(message = "contents shouldn't be empty")
    @Column(name = "content", nullable = false)
    private String content;

    @OneToMany(
            mappedBy = "post",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<Comment> comments = new HashSet<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }
}
