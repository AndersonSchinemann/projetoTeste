package com.evision.controller;


import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.evision.model.Usuario;
import com.evision.service.AuthenticationService;
import com.evision.service.UserDetailsServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;




//Controller privado responsavel pela autenticação do usuario
@RestController
//@RequestMapping("/auth")
public class LoginController {
	

	 @Autowired
	 private AuthenticationService authenticationService;

	 private UserDetailsServiceImpl userDetailsServiceImpl;

	 public LoginController() {
	 }
	 
	// private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	 @PostMapping("/process")
	 public ResponseEntity<?> authenticacao(@RequestBody Usuario loginRequest){
		 String username = loginRequest.getEmail();
		 String password = loginRequest.getPassword();
	     System.out.print("UsernameAutenticação -----------------." +  username + " "+password+"\n");

	     // Faça o processamento de autenticação com os dados recebidos

	     return ResponseEntity.ok().build(); 
	 }
//	

}
