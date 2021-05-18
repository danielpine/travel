package com.demo.travel.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"userId", "postId"})})
public class Favorite implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;


    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    private User user;


    @ManyToOne(targetEntity = Post.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "postId")
    private Post post;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }


}