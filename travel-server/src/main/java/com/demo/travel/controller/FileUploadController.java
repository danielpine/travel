package com.demo.travel.controller;

import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.demo.travel.bo.Result;
import com.demo.travel.service.StorageService;
import com.demo.travel.service.UserService;
import com.demo.travel.util.ResultUtil;

@Controller
@RequestMapping("/file")
public class FileUploadController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private StorageService storageService;

	@Autowired
	private UserService userService;

	@PostMapping("/upload")
	@ResponseBody
	public Result<?> handleFileUpload(@RequestParam("file") MultipartFile file, String id, String type) {
		logger.info("calling handleFileUpload()");
		logger.info(System.getProperty("user.dir"));
		logger.info("id " + id);
		logger.info("type {}", type);
		String filename = file.getOriginalFilename();
		String url = "/static/upload/" + filename;
		storageService.store(file);
		if (StringUtils.equals(type, "avator")) {
			logger.info("updateAvatorById ..");
			userService.updateAvatorById(id, url);
		}
		HashMap<String, String> res = new HashMap<>();
		res.put("url", url);
		res.put("name", filename);
		return ResultUtil.success(res);
	}
}
