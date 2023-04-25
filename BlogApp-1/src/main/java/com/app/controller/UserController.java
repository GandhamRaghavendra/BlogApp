package com.app.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.app.exception.LoginException;
import com.app.model.Blog;
import com.app.model.Comments;
import com.app.model.LoginDTO;
import com.app.model.User;
import com.app.service.BlogService;
import com.app.service.CommentsService;
import com.app.service.LoginService;
import com.app.service.UserService;

@RestController
@RequestMapping("blog/user")
public class UserController {

	@Autowired
	private UserService uService;

	@Autowired
	private LoginService loginService;

	@Autowired
	private BlogService blogService;
	
	@Autowired
	private CommentsService commentsService;

	// For user registration to app.
	@PostMapping("/reg")
	public ResponseEntity<User> registerUser(@RequestBody User user) {
		User u = uService.registerUser(user);
		return new ResponseEntity<User>(u, HttpStatus.OK);
	}

	// For login user.
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody LoginDTO dto) throws LoginException {
		String key = loginService.LoginToAccount(dto);

		return new ResponseEntity<>(key, HttpStatus.OK);
	}

	// For logout user.
	@PostMapping("/logout/{key}")
	public ResponseEntity<String> logout(@PathVariable String key) throws LoginException {
		String mes = loginService.LogOutFromAccount(key);

		return new ResponseEntity<>(mes, HttpStatus.OK);
	}

	// For posting a blog.
	@PostMapping("createBlog/{key}")
	public ResponseEntity<Blog> saveBlog(@RequestBody Blog blog, @PathVariable String key) throws LoginException {
		Blog res = blogService.saveBlog(blog, key);

		return new ResponseEntity<Blog>(res, HttpStatus.CREATED);
	}

	// For getting all blogs of user.
	@GetMapping("blogs/{key}")
	public ResponseEntity<List<Blog>> getAllBlogsByUser(@PathVariable String key) throws LoginException {
		List<Blog> res = uService.getAllBlog(key);

		return new ResponseEntity<List<Blog>>(res, HttpStatus.OK);
	}

	// For getting all blog by category.
	@GetMapping("{key}/blogs/{cat}")
	public ResponseEntity<List<Blog>> getAllBlogsByUser(@PathVariable String key, @PathVariable String cat)
			throws LoginException {
		List<Blog> res = blogService.getListOfBlogByCategory(cat, key);

		return new ResponseEntity<List<Blog>>(res, HttpStatus.OK);
	}
	
	// For writing comment on blog.
	@PostMapping("{key}/{bId}")
	public ResponseEntity<Comments> writeCommentOnOtherBlog(@PathVariable String key,@PathVariable Integer bId,@RequestBody Comments comments) throws LoginException{ 
		
		Comments res = commentsService.addComment(key, bId, comments);
		
		return new ResponseEntity<Comments>(res,HttpStatus.OK);
	}
	
	
	@DeleteMapping("{key}/comment/{c_id}")
	public ResponseEntity<Comments> deleteCommentOnYourBlog(@PathVariable String key,@PathVariable Integer c_id) throws LoginException{
		Comments com = commentsService.deleteCommentOnYourBlog(key, c_id);
		
		return new ResponseEntity<Comments>(com,HttpStatus.OK);
	}
	
	
	
	
	
	
	
	
}
