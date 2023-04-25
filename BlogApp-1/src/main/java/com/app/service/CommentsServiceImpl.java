package com.app.service;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.exception.BlogException;
import com.app.exception.CommentsException;
import com.app.exception.LoginException;
import com.app.model.Blog;
import com.app.model.Comments;
import com.app.model.User;
import com.app.repo.BlogRepo;
import com.app.repo.CommentsRepo;

@Service
public class CommentsServiceImpl implements CommentsService{

	@Autowired
	private UserValidator validator;
	
	@Autowired
	private CommentsRepo commentsRepo;
	
	@Autowired
	private BlogRepo blogRepo;
	
	
	@Override
	public Comments addComment(String key, Integer bId, Comments comment) throws LoginException {
		User user = validator.valid(key);
	
		Blog blog = blogRepo.findById(bId).orElseThrow(()-> new BlogException("Invalid BlogId"));
		
		if(blog.getUser().getUId() == user.getUId()) throw new BlogException("You can't comment on your own blog.!!");
		
		comment.setBlog(blog);
		
		comment.setDateTime(LocalDateTime.now());
		
		return commentsRepo.save(comment);
	}


	@Override
	public Comments deleteCommentOnYourBlog(String key, Integer commentId) throws LoginException {
		User user = validator.valid(key);
		
		List<Blog> bloglist = user.getBlogs();
		
		Comments com = commentsRepo.findById(commentId).orElseThrow(()-> new CommentsException("Invalid CommentId"));
		
		
		
		for(Blog b : bloglist) {
		
			if(b.getBlogId() == com.getBlog().getBlogId()) {
				commentsRepo.delete(com);
				
				return com;
			}
		}
		
		throw new CommentsException("This is comment is not related to your blog..!");
		
	}
	
	
	
	
	
	
	
	
	
	
	
}
