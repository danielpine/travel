package com.demo.travel.entity;

import java.util.Date;

import org.springframework.data.rest.core.config.Projection;

import com.fasterxml.jackson.annotation.JsonFormat;

@Projection(name = "postProjection", types = { PostProjection.class })
public interface PostProjection {
	User getUser();
	Long getId();
	String getText();
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	Date getPostTime();
}
