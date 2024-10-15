package com.example.sellcar.controllers;

import com.example.sellcar.dto.AuthenticationRequest;
import com.example.sellcar.dto.AuthenticationResponse;
import com.example.sellcar.dto.SignUpRequest;
import com.example.sellcar.dto.UserDTO;
import com.example.sellcar.entity.User;
import com.example.sellcar.repository.UserRepository;
import com.example.sellcar.services.auth.AuthService;
import com.example.sellcar.services.jwt.UserService;
import com.example.sellcar.util.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    @Autowired
    AuthService authService;

    private final JWTUtil jwtUtil;
    private final UserService userService;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignUpRequest signupRequest) {
        if (authService.hasUserWithEmail(signupRequest.getEmail()))
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("User already exist");
        UserDTO userDTO = authService.signUp(signupRequest);
        if (userDTO == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        return ResponseEntity.status(HttpStatus.CREATED).body(userDTO);

    }

    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody AuthenticationRequest request){
        System.out.println("3");
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        }
        catch (Exception E){
            throw new BadCredentialsException("Invalid Credentials");
        }
        UserDetails userDetails=userService.userDetailsService().loadUserByUsername(request.getEmail());
        Optional<User> optionalUser = userRepository.findFirstByEmail(request.getEmail());
        final String jwt;
        jwt = jwtUtil.generateToken(userDetails);
        AuthenticationResponse response = new AuthenticationResponse();
        if (optionalUser.isPresent()) {
            response.setJwt(jwt);
            response.setId(optionalUser.get().getId());
            response.setRole(optionalUser.get().getUserRole());

        }
        System.out.println(response.toString());
            return response;
    }
}
