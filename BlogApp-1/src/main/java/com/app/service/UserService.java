package com.app.service;

import java.util.List;

import com.app.exception.LoginException;
import com.app.model.Blog;
import com.app.model.Comments;
import com.app.model.User;

public interface UserService {
	public User registerUser(User user);
	
	public List<Blog> getAllBlog(String key) throws LoginException;
	
	public Blog deleteBlogById(String key,Integer bId);
	
	public Comments deleteCommentsById(String key,Integer cId);
}
