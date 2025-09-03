package com.wipro.sk.services.auth;

import com.wipro.sk.dto.SignupRequest;
import com.wipro.sk.dto.UserDto;

public interface AuthService 
{

	UserDto signupUser(SignupRequest signupRequest);
	boolean hasUserWithEmail(String email);
}
