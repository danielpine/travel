package com.demo.travel.dao;

import com.demo.travel.entity.Favorite;
import com.demo.travel.entity.FavoriteProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface FavoriteDao extends JpaRepository<Favorite, Long> {

    int countByPostIdAndUserId(Long postId, String userId);

    FavoriteProjection findByPostIdAndUserId(Long postId, String userId);

    @Query("select f from Favorite f join Post p on f.post.id = p.id where f.user.id=:userId and f.post.postType=:postType")
    List<Favorite> findByUserIdAndPostType(String userId, String postType);

}
