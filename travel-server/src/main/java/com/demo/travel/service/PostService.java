package com.demo.travel.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.travel.dao.PostDao;
import com.demo.travel.entity.FavoriteTopicProjection;
import com.demo.travel.entity.Post;

@Service
@Transactional
public class PostService {

    @Autowired
    private PostDao postDao;

    @PersistenceContext
    private EntityManager entityManager;

    public List<Post> recommend(Long userId, String postType) {
        Session session = (Session) entityManager.getDelegate();
        ArrayList<FavoriteTopicProjection> list = new ArrayList<>();
        List<FavoriteTopicProjection> favoriteTopics = postDao.findFavoriteTopic(userId, postType);
        list.addAll(favoriteTopics);
        List<FavoriteTopicProjection> thumbTopics = postDao.findThumbFavoriteTopic(userId, postType);
        list.addAll(thumbTopics);
        List<FavoriteTopicProjection> commentTopics = postDao.findCommentFavoriteTopic(userId, postType);
        list.addAll(commentTopics);
        if (!favoriteTopics.isEmpty()) {
            Map<String, List<FavoriteTopicProjection>> map = list.stream().collect(
                    Collectors.groupingBy(FavoriteTopicProjection::getTopic));
            Integer sum = map.values().stream().mapToInt(List::size).reduce(Integer::sum).orElse(0);
            ArrayList<Topic> topics = new ArrayList<>();
            map.forEach((topic, habitualList) -> {
                Topic t = new Topic();
                t.setScore(habitualList.size());
                t.setTopic(topic);
                topics.add(t);
                System.out.println(t);
            });
            topics.sort((a, b) -> b.getScore() - a.getScore());
            ArrayList<Post> recommendPostList = new ArrayList<>();
            topics.stream().forEach(t -> {
                String topic = t.getTopic();
                Integer score = t.getScore();
                Integer limit = (int) Math.ceil(((((float) score) / sum) * 20));
                System.out.println("limit size " + limit);
                @SuppressWarnings("unchecked")
                Query<Post> query = session.createQuery("from Post where postType=:postType and topic=:topic")
                        .setFirstResult(0).setMaxResults(limit);
                query.setParameter("postType", postType);
                query.setParameter("topic", topic);
                List<Post> postList = query.list();
                System.out.println("postList size " + topic + " " + postList.size());
                recommendPostList.addAll(postList);
            });
            return recommendPostList;
        } else {
            Sort sort = new Sort(Sort.DEFAULT_DIRECTION, "postTime");
            Pageable pageable = PageRequest.of(0, 20, sort);
            return postDao.findByPostType("topic", pageable).stream().collect(Collectors.toList());
        }
    }

    class Topic {
        String topic;
        Integer score;

        public String getTopic() {
            return topic;
        }

        public void setTopic(String topic) {
            this.topic = topic;
        }

        public Integer getScore() {
            return score;
        }

        public void setScore(Integer score) {
            this.score = score;
        }

        @Override
        public String toString() {
            return "Topic [topic=" + topic + ", score=" + score + "]";
        }

    }

}
