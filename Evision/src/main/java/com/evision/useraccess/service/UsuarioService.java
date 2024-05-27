package com.evision.useraccess.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.evision.useraccess.model.Usuario;
import com.evision.useraccess.repository.ProfileRepository;
import com.evision.useraccess.repository.UsuarioRepository;


@Service
public class UsuarioService{
	

	@Autowired
	private UsuarioRepository userRepository;
	@Autowired
    private  ProfileRepository profileRepository;
	@Autowired
	protected BCryptPasswordEncoder bCryptPasswordEncoder;
	
    
	public UsuarioService() {
       
        
    }
	
	
	 
	
//    public Optional<Usuario> findByUserEmail(String username) {
//    	return userRepository.findByEmail(username);
//    }
	
    public Optional<Usuario> findByUserEmail(String username) {
    	
        Optional<Usuario> usuario = userRepository.findByEmail(username);
        if (usuario.isEmpty()) {
            // Trate o caso de usuário não encontrado
        	 System.out.println("Erro em encontrar o usuario");
        	 usuario=null;
        }
        return usuario;
    }
    
    
    public void save(Usuario usuario) {
    		System.out.println("usuario salvo");
    	 	String encodedPassword = bCryptPasswordEncoder.encode(usuario.getPassword());
    	 	usuario.setPassword(encodedPassword);
	        profileRepository.save(usuario.getProfile());
	        userRepository.save(usuario);
	}
    public Usuario getUsuario(String userId) {
    	Long id = Long.parseLong(userId);
        return userRepository.findById(id).get();	
    }
    public List<Usuario> getAllUsuarios() {
        return userRepository.findAll();
    }

    public Usuario getUsuarioById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

//    public List<Usuario> searchUsuarios(String searchTerm) {
//        return userRepository.findByNameContainingAndEmailContaining(searchTerm, searchTerm);
//    }
    
    public void deleteUsuarioAll(Usuario usuario) {
    	userRepository.delete(usuario);
    }
    
    public boolean deleteUsuario(long id) {
    	try {
            userRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            
           return false;
        }
    }
    
    public List<Usuario> pesquisaUsuario(String searchText, boolean searchMode){
    	System.out.println("Procurando usuario Sevice");
    	return userRepository.findByNameContainingOrEmailContaining(searchText,searchMode);
    }
    
    public Usuario atualizarUsuario(Usuario usuarioAtualizado) {
        // Verifique se o usuário existe antes de tentar atualizar
        if (userRepository.existsById(usuarioAtualizado.getId())) {
            return userRepository.save(usuarioAtualizado);
        } else {
            // Lógica de tratamento se o usuário não existir
            return null;
        }
    }

    
}
