package com.evision.model;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;


@Entity
@Table(name = "usuario")
public class Usuario{
	

	 @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @JsonProperty("id")
	 @Column(name = "id")
	 private long id;
	 
	 @JsonProperty("nome")
	 @Column(name = "name")
	 private String name;
	 
	 @JsonProperty("email")
	 @Column(name = "email")
	 private String email;
	 
	 @JsonProperty("senha")
	 @Column(name = "password")
	 private String password;
	 
	  @OneToOne(cascade = CascadeType.ALL)
	  @JoinColumn(name = "profile_id", referencedColumnName = "id")
	  @JsonManagedReference
	  private Profile profile;
	  
	  @Transient
	  private Authentication authentication;
	 
	 public Usuario() {
	        // Construtor padrão necessário para JPA
	 }
	  
	  
//	  public Authentication getAuthentication() {
//
//	        // Obtém o objeto de autenticação da injeção de dependência
//	        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//	        // Retorna o objeto de autenticação
//	        return authentication;
//	    }
 
	public long getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}
	
//	public Set<Profile> getPerfil() {
//        // Implementation to retrieve a Set<Profile> from the Usuario object
//        // Example:
//        Set<Profile> profiles = new HashSet<>();
//        return profiles;
//    }
	
	public String getPerfil() {
        // implementação do profile retornando somente a descrição do do nivel de acesso usuario
        
        return profile.getDescricao();
    }
	
	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Profile getProfile() {
		return profile;
	}
	
	public void setProfile(Profile profile) {
		this.profile = profile;
	}



}
