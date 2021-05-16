package com.demo.travel.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.demo.travel.entity.Post;
import com.demo.travel.entity.PostProjection;

@RepositoryRestResource(excerptProjection = PostProjection.class)
public interface PostDao extends JpaRepository<Post, Long>, JpaSpecificationExecutor<Post> {
}
