package com.evision.useraccess.model;



import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;



@Entity
@Table(name = "profile")
public class Profile {

	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("id")
	@Column(name = "id")
	private Long id;
    
	@JsonProperty("perfil")
	@Column(name = "name")
    private String name;

	@JsonBackReference
	@OneToOne(mappedBy = "profile")
    private Usuario user;
    
    public Profile() {
        // Construtor padrão necessário para JPA
    }

	public Long getId() {
		return id;
	}
//
//	public void setId(int id) {
//		this.id = id;
//	}

	public String getDescricao() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

    

}

