package com.evision.useraccess.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.evision.useraccess.service.AuthenticationService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;





//Controller privado responsavel pela autenticação do usuario
@RestController
public class LoginController {
	
	
     private final AuthenticationService authenticationService;
	 @Autowired
	 private AuthenticationManager authenticationManager;
	 private Authentication authentication;
	 
	 public LoginController( AuthenticationService authenticationService) {
	        this.authenticationService = authenticationService;
	 }
	 
	 
	 @PostMapping("/authenticate")
	 public ResponseEntity<?> authenticate(@RequestBody Map<String, String> loginRequest, HttpServletRequest request) {
		 
		 
	        // Cria um token de autenticação com base nos dados recebidos
		 String email = loginRequest.get("email");
	     String password = loginRequest.get("password");
	    
	     UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email,password);
	     
	     
	     // Se a autenticação for bem-sucedida, retorne uma resposta HTTP 200 OK
	     try {
	    	   authentication = authenticationManager.authenticate(authenticationToken);
	    	   String token = authenticationService.authenticate(authentication); 
		    
		    	Map<String, String> response = new HashMap<>();
		    	HttpSession session = request.getSession(true);
			    SecurityContext securityContext = SecurityContextHolder.getContext();
			    
			    securityContext.setAuthentication(authentication);
			    session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, securityContext);
			    System.out.println("auth "+ authentication.getAuthorities());
			    System.out.println(token);
			       // Cria um mapa de resposta com o token
		              response.put("token", token);
			          return ResponseEntity.ok(response);
			 } catch (AuthenticationException e) {
		         // Tratar exceção de autenticação
		         return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed: " + e.getMessage());
		     }
 
	   }
	  
	
}
