package com.demo.travel.dao;

import com.demo.travel.entity.Post;
import com.demo.travel.entity.PostProjection;
import com.demo.travel.entity.Thumb;
import com.demo.travel.entity.ThumbProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(excerptProjection = ThumbProjection.class)
public interface ThumbDao extends JpaRepository<Thumb, Long> {
}
