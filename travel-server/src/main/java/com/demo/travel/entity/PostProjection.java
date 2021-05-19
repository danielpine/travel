package com.demo.travel.entity;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.data.rest.core.config.Projection;

import com.fasterxml.jackson.annotation.JsonFormat;

@Projection(name = "postProjection", types = { PostProjection.class })
public interface PostProjection {
	Long getId();
	String getText();
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	Date getPostTime();
	Set<Image> getImage();
	Location getLocation();
	UserProjection getUser();
	Set<ThumbProjection> getThumb();
	List<CommentProjection> getComment();
}
