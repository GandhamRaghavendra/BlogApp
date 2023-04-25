package com.app.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.app.exception.LoginException;
import com.app.model.CurrentUserSession;
import com.app.model.LoginDTO;
import com.app.model.User;
import com.app.repo.SessionRepo;
import com.app.repo.UserRepo;
import com.app.utility.RandomString;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private UserRepo urepo;

	@Autowired
	private SessionRepo sessionRepo;

	@Override
	public String LoginToAccount(LoginDTO loginDTO) throws LoginException {
		
		Optional<User> use = urepo.findByMail(loginDTO.getMail());

		if (!use.isPresent())
			throw new LoginException("Please Enter Valid Mail");

		Optional<CurrentUserSession> sess = sessionRepo.findById(loginDTO.getMail());

		if (sess.isPresent())
			throw new LoginException("User Already LogedIn With This Mail");            

		if (use.get().getPassword().equals(loginDTO.getPassword())) {
			
			String key = RandomString.randomString(6);

			CurrentUserSession currentUserSession = new CurrentUserSession();

			currentUserSession.setKey(key);
			currentUserSession.setMail(use.get().getMail());

			sessionRepo.save(currentUserSession);

			return key;
		}

		throw new LoginException("Enter Valid Password");

	}

	
	@Override
	public String LogOutFromAccount(String Key) throws LoginException {
		CurrentUserSession sess = sessionRepo.findByKey(Key);

		if (sess == null)
			throw new LoginException("Customer Not Logged In with this Number");

		else {
			sessionRepo.delete(sess);
			return "Logged Out !!";
		}
	}

}
