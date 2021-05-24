package com.demo.travel.entity;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.data.rest.core.config.Projection;

import com.fasterxml.jackson.annotation.JsonFormat;

@Projection(name = "postProjection", types = { TopicProjection.class })
public interface TopicProjection {
	Long getId();
	String getTitle();
	String getText();
	String getTopic();
	String getStatus();
	String getPostType();
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	Date getPostTime();
	Set<Image> getImage();
	Location getLocation();
	UserProjection getUser();
	Set<Thumb> getThumb();
	List<CommentProjection> getComment();
}
