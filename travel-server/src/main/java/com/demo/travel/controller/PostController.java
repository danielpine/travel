package com.demo.travel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.demo.travel.bo.Result;
import com.demo.travel.entity.Post;
import com.demo.travel.service.PostService;
import com.demo.travel.util.ResultUtil;

/**
 * 控制器层
 *
 * @author Administrator
 */
@RestController
@CrossOrigin
@RequestMapping("/posts")
public class PostController {

	@Autowired
	private PostService postService;

	@RequestMapping(value = "/recommend", method = RequestMethod.GET)
	public Result<?> recommend(Long userId, String postType) {
		System.out.println(userId);
		System.out.println(postType);
		List<Post> list = postService.recommend(userId, postType);
		return ResultUtil.success(list);
	}

}
