package com.shriram.ecom.services.auth;

import com.shriram.ecom.dto.SignupRequest;
import com.shriram.ecom.dto.UserDto;

public interface AuthService {
    UserDto createUser(SignupRequest signupRequest);

    boolean hasUserWithEmail(String email);
}
