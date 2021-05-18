package com.demo.travel.dao;


import com.demo.travel.entity.Comment;
import com.demo.travel.entity.CommentProjection;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(excerptProjection = CommentProjection.class)
public interface CommentDao extends CrudRepository<Comment, Long> {
    List<Comment> findByPostIdAndCommentType(@Param("postId") Long postId, @Param("commentType") String commentType);
}
