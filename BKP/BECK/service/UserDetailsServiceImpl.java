package com.evision.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import com.evision.repository.UsuarioRepository;
import com.evision.security.UserAuthenticated;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	
	private final UsuarioRepository usuarioRepository;
	
	
	public UserDetailsServiceImpl(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.print("UserDetails Procurando Usuario --->>  "+ username +" ");
		return usuarioRepository.findByEmail(username)
	        .map(usuario -> new UserAuthenticated(usuario))
	        .orElseThrow(
	            () -> new UsernameNotFoundException("User Not Found with username: " + username));
	 }

}
