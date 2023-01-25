package com.stanford.springjwt.controllers;

import com.stanford.springjwt.payload.request.LoginRequest;
import com.stanford.springjwt.payload.request.SignupRequest;
import com.stanford.springjwt.payload.response.JwtResponse;
import com.stanford.springjwt.payload.response.MessageResponse;
import com.stanford.springjwt.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {



    @Autowired
    AuthService authService;


  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

      JwtResponse jwtResponse = authService.authenticateUserService(loginRequest.getUsername(),loginRequest.getPassword());
      return ResponseEntity.ok(jwtResponse);
  }

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {

    authService.registerUserService(signUpRequest);
    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
  }
}
