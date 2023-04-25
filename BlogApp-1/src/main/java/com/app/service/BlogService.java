package com.app.service;

import java.util.List;

import com.app.exception.LoginException;
import com.app.model.Blog;

public interface BlogService {

	Blog saveBlog(Blog blog,String key) throws LoginException;
	
	List<Blog> getListOfBlogByCategory(String cat,String key) throws LoginException;
}
