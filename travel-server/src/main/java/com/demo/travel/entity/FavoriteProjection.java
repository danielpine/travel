package com.demo.travel.entity;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "favoriteProjection", types = {FavoriteProjection.class})
public interface FavoriteProjection {
    Long getId();
}
