package com.demo.travel.entity;

import com.demo.travel.util.Dates;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Comment {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Post post;

    private String commentType;

    @ManyToOne
    private User user;

    @ManyToOne
    private User toUser;

    private String content;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createTime;

    @ManyToOne
    private Comment root;

    @OneToMany(mappedBy = "root", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comment> sub;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Comment getRoot() {
        return root;
    }

    public void setRoot(Comment root) {
        this.root = root;
    }

    public List<Comment> getSub() {
        return sub;
    }

    public void setSub(List<Comment> sub) {
        this.sub = sub;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCommentType() {
        return commentType;
    }

    public void setCommentType(String commentType) {
        this.commentType = commentType;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getToUser() {
        return toUser;
    }

    public void setToUser(User toUser) {
        this.toUser = toUser;
    }
}
