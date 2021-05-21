package com.demo.travel.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.demo.travel.entity.Post;
import com.demo.travel.entity.PostProjection;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

@RepositoryRestResource(excerptProjection = PostProjection.class)
public interface PostDao extends JpaRepository<Post, Long>, JpaSpecificationExecutor<Post> {
    @RestResource(path = "findByPostType", rel = "findByPostType")
    Page<Post> findByPostType(String postType, Pageable pageable);
}
