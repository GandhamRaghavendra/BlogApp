package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.exception.LoginException;
import com.app.model.Blog;
import com.app.model.Comments;
import com.app.model.User;
import com.app.repo.UserRepo;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepo urepo;
	
	@Autowired
	private UserValidator validator;
	
	@Override
	public User registerUser(User user) {
		return urepo.save(user);
	}

	@Override
	public List<Blog> getAllBlog(String key) throws LoginException {
		User user = validator.valid(key);
		
		return user.getBlogs();
	}

	@Override
	public Blog deleteBlogById(String key, Integer bId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comments deleteCommentsById(String key, Integer cId) {
		// TODO Auto-generated method stub
		return null;
	}

}
