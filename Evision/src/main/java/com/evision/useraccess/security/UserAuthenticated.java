package com.evision.useraccess.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.evision.useraccess.model.Profile;
import com.evision.useraccess.model.Usuario;

public class UserAuthenticated  implements UserDetails {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Usuario usuario;
	
//	public UserAuthenticated(Usuario usuario) {
//		this.usuario = usuario;
//	}
	 public UserAuthenticated(Usuario usuario) {
	       this.usuario = usuario;

	 }
	
//	@Override
//	  public Collection<? extends GrantedAuthority> getAuthorities() {
//	    return List.of(() -> "read");
//	  }
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
	    // Crie uma lista para armazenar as autoridades
	    List<GrantedAuthority> authorities = new ArrayList<>();
	    
	    // Adicione as autoridades desejadas
	    authorities.add(new SimpleGrantedAuthority(usuario.getPerfil()));//usuario.getPerfil() nivel de acesso do usuario
	    // Adicione outras autoridades conforme necessário
	    // Retorne a lista de autoridades
	    return authorities;
	}
	 
	 @Override
	  public String getUsername() {
	    return usuario.getEmail();
	  }

	  @Override
	  public String getPassword() {
	    return usuario.getPassword();
	  }
	  
	  @Override
	  public boolean isAccountNonExpired() {
	    return true;
	  }

	  @Override
	  public boolean isAccountNonLocked() {
	    return true;
	  }

	  @Override
	  public boolean isCredentialsNonExpired() {
	    return true;
	  }

	  @Override
	  public boolean isEnabled() {
	    return true;
	  }
}
