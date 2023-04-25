package com.app.service;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.app.exception.LoginException;
import com.app.model.Blog;
import com.app.model.Category;
import com.app.model.User;
import com.app.repo.BlogRepo;
import com.app.repo.SessionRepo;
import com.app.repo.UserRepo;


@Service
public class BlogServiceImpl implements BlogService{

	@Autowired
	private BlogRepo blogRepo;
	
	@Autowired
	private UserRepo urepo;
	
	@Autowired
	private SessionRepo sessionRepo;
	
	@Autowired
	private UserValidator validator;
	
	
	
	@Override
	public Blog saveBlog(Blog blog,String key) throws LoginException {
		
//		CurrentUserSession sess = sessionRepo.findByKey(key);
//		
//		if(sess == null) throw new LoginException("Not logedId");
//		
//		Optional<User> use = urepo.findByMail(sess.getMail());
		
		User user = validator.valid(key);
		
		blog.setUser(user);
		
		blog.setDateTime(LocalDateTime.now());
		
		return blogRepo.save(blog);
	}

	@Override
	public List<Blog> getListOfBlogByCategory(String cat,String key) throws LoginException {
		validator.valid(key);
		
		List<Blog> res = blogRepo.findByCategory(Category.valueOf(cat));
		
		return res;
	}

}
