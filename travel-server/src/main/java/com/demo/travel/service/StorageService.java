package com.demo.travel.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.apache.commons.io.FileUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class StorageService {
	private final Path rootLocation = Paths.get("/data/upload");

	public void store(MultipartFile file) {
		String filename = StringUtils.cleanPath(file.getOriginalFilename());
		try {
			if (file.isEmpty()) {
				throw new RuntimeException("File is empty： " + filename);
			}
			if (filename.contains("..")) {
				// 文件路径安全校验
				throw new RuntimeException(
				        "不能将文件保存到相对目录路径中： "
				                + filename);
			}
			// 将上传的文件保存到指定位置
			FileUtils.copyInputStreamToFile(file.getInputStream(),
			        this.rootLocation.resolve(filename).toFile());
		} catch (IOException e) {
			throw new RuntimeException("上传文件失败 " + filename, e);
		}
	}

	public Stream<Path> loadAll() {
		try {
			return Files.walk(this.rootLocation, 1)
			        .filter(path -> !path.equals(this.rootLocation))
			        .map(path -> this.rootLocation.relativize(path));

		} catch (IOException e) {
			throw new RuntimeException("Failed to read stored files", e);
		}
	}

	public Path load(String filename) {
		return rootLocation.resolve(filename);
	}

	public Resource loadAsResource(String filename) {
		try {
			Path file = load(filename);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new RuntimeException(
				        "Could not read file: " + filename);
			}
		} catch (MalformedURLException e) {
			throw new RuntimeException("Could not read file: " + filename, e);
		}
	}

	public void deleteAll() {
		FileSystemUtils.deleteRecursively(rootLocation.toFile());
	}

	public void init() {
		try {
			Files.createDirectories(rootLocation);
		} catch (IOException e) {
			throw new RuntimeException("Could not initialize storage", e);
		}
	}

}
