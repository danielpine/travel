package com.demo.travel.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.demo.travel.entity.Image;

@RepositoryRestResource(exported = false)
public interface ImageDao extends JpaRepository<Image, Long>, JpaSpecificationExecutor<Image> {
}
