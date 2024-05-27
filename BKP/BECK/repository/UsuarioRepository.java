package com.evision.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.evision.model.Usuario;


import jakarta.transaction.Transactional;


public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // métodos do repositório
	
//    @Query("SELECT u FROM Usuario u LEFT JOIN FETCH u.profile")
//    List<Usuario> findAllWithProfile();
    
	
	Optional<Usuario> findByEmail(String name);
	
	
    
    @Transactional
    int deleteUsuarioById(int id);

    List<Usuario> findByNameContainingOrEmailContaining(String searchText, boolean searchMode);

	boolean existsById(int id);

 
}
