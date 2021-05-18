package com.demo.travel.dao;

import com.demo.travel.entity.Favorite;
import com.demo.travel.entity.FavoriteProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface FavoriteDao extends JpaRepository<Favorite, Long> {

    int countByPostIdAndUserId(Long postId, String userId);

    FavoriteProjection findByPostIdAndUserId(Long postId, String userId);

}
