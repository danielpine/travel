package com.demo.travel.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.rest.core.config.Projection;

import java.util.Date;
import java.util.Set;

@Projection(name = "thumbProjection", types = {ThumbProjection.class})
public interface ThumbProjection {
    Long getId();
    String getUserId();
}
