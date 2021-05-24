package com.demo.travel.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import com.demo.travel.entity.FavoriteTopicProjection;
import com.demo.travel.entity.Post;
import com.demo.travel.entity.PostProjection;
import com.demo.travel.entity.TopicProjection;

@RepositoryRestResource(excerptProjection = PostProjection.class)
public interface PostDao extends JpaRepository<Post, Long>, JpaSpecificationExecutor<Post> {
    @RestResource(path = "findByPostType", rel = "findByPostType")
    Page<Post> findByPostType(String postType, Pageable pageable);

    @RestResource(path = "findByPostTypeAndUserId", rel = "findByPostTypeAndUserId")
    Page<Post> findByPostTypeAndUserId(String postType, String userId, Pageable pageable);

    @RestResource(path = "findByIdAndPostType", rel = "findByIdAndPostType")
    Optional<TopicProjection> findByIdAndPostType(Long id, String postType);

    @Query(value = "select p.id as id,p.topic as topic from post p join favorite f on p.id=f.post_id where "
            + "p.post_type=:postType and p.user_id=:userId ", nativeQuery = true)
    @RestResource(exported = false)
    List<FavoriteTopicProjection> findFavoriteTopic(@Param("userId") Long userId, @Param("postType") String postType);

    @Query(value = "select p.id as id,p.topic as topic from post p join thumb f on p.id=f.post_id where "
            + "p.post_type=:postType and p.user_id=:userId ", nativeQuery = true)
    @RestResource(exported = false)
    List<FavoriteTopicProjection> findThumbFavoriteTopic(@Param("userId") Long userId,
                                                         @Param("postType") String postType);

    @Query(value = "select p.id as id,p.topic as topic from post p join `comment` f on p.id=f.post_id where "
            + "p.post_type=:postType and p.user_id=:userId ", nativeQuery = true)
    @RestResource(exported = false)
    List<FavoriteTopicProjection> findCommentFavoriteTopic(@Param("userId") Long userId,
                                                           @Param("postType") String postType);
}
