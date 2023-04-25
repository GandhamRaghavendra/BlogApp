package com.app.service;

import com.app.exception.LoginException;
import com.app.model.Comments;

public interface CommentsService {

	public Comments addComment(String key,Integer bId,Comments comments) throws LoginException;
	
	public Comments deleteCommentOnYourBlog(String key,Integer commentId) throws LoginException;
}
