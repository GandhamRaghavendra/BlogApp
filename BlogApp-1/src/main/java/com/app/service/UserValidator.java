package com.app.service;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app.exception.LoginException;
import com.app.model.CurrentUserSession;
import com.app.model.User;
import com.app.repo.SessionRepo;
import com.app.repo.UserRepo;

@Component
public class UserValidator {

	@Autowired
	private SessionRepo sessionRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	public User valid(String k) throws LoginException {
		
		CurrentUserSession ses = sessionRepo.findByKey(k);
		
		if(ses==null) throw new LoginException("User Not LoggedIn..!!");
		
		Optional<User> user = userRepo.findByMail(ses.getMail());
		
		return user.get();
	}
}
