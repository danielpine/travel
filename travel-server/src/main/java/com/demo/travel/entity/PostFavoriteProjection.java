package com.demo.travel.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.rest.core.config.Projection;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface PostFavoriteProjection {
    Post getPost();
    Favorite getFavorite();
}
