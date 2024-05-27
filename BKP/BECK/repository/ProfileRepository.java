package com.evision.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.evision.model.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
    // métodos do repositório
	
}