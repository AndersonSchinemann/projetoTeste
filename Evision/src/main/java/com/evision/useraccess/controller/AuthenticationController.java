package com.evision.useraccess.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.evision.useraccess.service.AuthenticationService;

@RestController
public class AuthenticationController {
  @Autowired
  private AuthenticationService authenticationService;
//
//  @PostMapping("authenticate")
//  public String authenticate(Authentication authentication) {
//	return authenticationService.authenticate(authentication);   
//  }
//  
// @PostMapping("/authenticate")
//  public String authenticate(@RequestParam("username") String username, @RequestParam("password") String password) {
//      SecurityContextHolder.clearContext(); // Clear existing context
//      Authentication authentication = new UsernamePasswordAuthenticationToken(username, password);
//      SecurityContextHolder.getContext().setAuthentication(authentication);
//      return authenticationService.authenticate(authentication);
//  }
}
