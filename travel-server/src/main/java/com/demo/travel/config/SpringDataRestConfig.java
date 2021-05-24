package com.demo.travel.config;

import com.demo.travel.entity.Comment;
import com.demo.travel.entity.Favorite;
import com.demo.travel.entity.Post;
import com.demo.travel.entity.Report;
import com.demo.travel.entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

@Configuration
public class SpringDataRestConfig {
	@Bean
	public RepositoryRestConfigurer repositoryRestConfigurer() {
		return new RepositoryRestConfigurerAdapter() {
			@Override
			public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
				config.exposeIdsFor(Report.class, Comment.class, User.class, Post.class, Favorite.class);
			}
		};
	}
}
