package com.demo.travel.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.demo.travel.util.Dates;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.rest.core.annotation.RestResource;

@Entity
public class Post implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    private String text;// 内容

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date postTime = new Date();// 时间

    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    private User user;

    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "postId")
    private Set<Image> image;

    @OneToMany(mappedBy = "post")
    private Set<Thumb> thumb;

    @OneToMany(mappedBy = "post")
    private List<Comment> comment;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getPostTime() {
        return postTime;
    }

    public void setPostTime(Date postTime) {
        this.postTime = postTime;
    }

    public Set<Image> getImage() {
//		image.forEach(e -> e.setPost(this));
        return image;
    }

    public void setImage(Set<Image> image) {
        this.image = image;
    }

    public Set<Thumb> getThumb() {
        return thumb;
    }

    public void setThumb(Set<Thumb> thumb) {
        this.thumb = thumb;
    }

    public List<Comment> getComment() {
        return comment.stream().sorted(Dates.DescComparator).collect(Collectors.toList());
    }

    public void setComment(List<Comment> comment) {
        this.comment = comment;
    }
}
