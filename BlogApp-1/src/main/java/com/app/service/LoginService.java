package com.app.service;

import com.app.exception.LoginException;
import com.app.model.LoginDTO;

public interface LoginService {
	public String LoginToAccount(LoginDTO loginDTO)throws LoginException;
	public String LogOutFromAccount(String Key)throws LoginException;
}
