package com.evision.teste;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;

import com.evision.model.Usuario;
import com.evision.repository.ProfileRepository;
import com.evision.repository.UsuarioRepository;
import com.evision.service.UsuarioService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class UsuarioServiceTest {

    @Mock
    private UsuarioRepository userRepository;

    @Mock
    private ProfileRepository profileRepository;

    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private UsuarioService usuarioService;

    @SuppressWarnings("deprecation")
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testFindByUserEmail() {
        // Simular comportamento do repositório
     //   when(userRepository.findByEmail("test@example.com")).thenReturn(new Usuario());

        // Executar o método de serviço
      //  Usuario usuario = usuarioService.findByUserEmail("test@example.com");

        // Verificar se o resultado não é nulo
      //  assertNotNull(usuario);
    }

    @Test
    void testSave() {
        /*Criar um usuário para salvar*/
        Usuario usuario = new Usuario();
        usuario.setEmail("test@example.com");

        // Executar o método de serviço
        usuarioService.save(usuario);

        // Verificar se o método do repositório foi chamado
        verify(profileRepository, times(1)).save(usuario.getProfile());
        verify(userRepository, times(1)).save(usuario);
    }

    @Test
    void testGetAllUsuarios() {
        // Simular comportamento do repositório
        when(userRepository.findAll()).thenReturn(new ArrayList<>());

        // Executar o método de serviço
        List<Usuario> usuarios = usuarioService.getAllUsuarios();

        // Verificar se a lista não é nula
        assertNotNull(usuarios);
    }

    // Adicione mais métodos de teste conforme necessário
}
