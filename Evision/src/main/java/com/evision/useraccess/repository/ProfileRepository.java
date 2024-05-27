package com.evision.useraccess.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.evision.useraccess.model.Profile;

@Repository
public interface ProfileRepository extends JpaRepository<Profile,Long> {
	
	
    // métodos do repositório
	
}


