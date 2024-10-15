package com.example.sellcar.services.auth;

import com.example.sellcar.dto.SignUpRequest;
import com.example.sellcar.dto.UserDTO;

public interface AuthService {
    Boolean hasUserWithEmail(String email);
    UserDTO signUp(SignUpRequest request);
}
