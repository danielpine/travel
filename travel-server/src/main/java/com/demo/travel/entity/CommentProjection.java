package com.demo.travel.entity;

import org.springframework.data.rest.core.config.Projection;

import java.util.Date;
import java.util.Set;

@Projection(name = "commentProjection", types = {Comment.class})
public interface CommentProjection {

    Long getId();

    Post getPost();

    User getUser();

    User getToUser();

    String getContent();

    String getCommentType();

    Date getCreateTime();

    Set<CommentProjection> getSub();
}
