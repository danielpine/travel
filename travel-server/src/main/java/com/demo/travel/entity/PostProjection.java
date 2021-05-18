package com.demo.travel.entity;

import java.util.Date;
import java.util.Set;

import org.springframework.data.rest.core.config.Projection;

import com.fasterxml.jackson.annotation.JsonFormat;

@Projection(name = "postProjection", types = { PostProjection.class })
public interface PostProjection {
	UserProjection getUser();
	Long getId();
	String getText();
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	Date getPostTime();
	Set<Image> getImage();
	Set<ThumbProjection> getThumb();
	Set<CommentProjection> getComment();
}
