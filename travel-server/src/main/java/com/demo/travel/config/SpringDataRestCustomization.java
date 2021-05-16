package com.demo.travel.config;

import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.stereotype.Component;

import com.demo.travel.dao.PostDao;
import com.demo.travel.entity.Post;

@Component
public class SpringDataRestCustomization implements RepositoryRestConfigurer {

//	@Override
//	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
//		config.withEntityLookup().forValueRepository(PostDao.class, Post::getId, PostDao::findById);
//	}

}
