package com.evision.useraccess.component;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//
//import com.evision.service.UserDetailsServiceImpl;
//
//@Component
//public class CustomAuthenticationProvider implements AuthenticationProvider {
//
//    @Autowired
//    private UserDetailsServiceImpl userServiceImpl;
//    private  UserDetails userDetails;
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        String username = authentication.getName();
//        String password = authentication.getCredentials().toString();
//
//        // Valide as credenciais do usuário (username e senha) de acordo com sua lógica personalizada
//        // ...
//        System.out.println("Dentro do meu autentication: ");
//
//        if (isValidCredentials(username, password)) {
//            // Crie um objeto Authentication personalizado com as informações de autenticação
//           userDetails = userServiceImpl.loadUserByUsername(username);
//            Authentication customAuth = new CustomAuthenticationToken(userDetails, password, userDetails.getAuthorities());
//            return customAuth;
//        } else {
//            throw new BadCredentialsException("Credenciais inválidas");
//        }
//    }
//
//    @Override
//    public boolean supports(Class<?> authentication) {
//        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
//    }
//
//    // Método para validar as credenciais do usuário (implemente sua lógica aqui)
//    private boolean isValidCredentials(String username, String password) {
//    	userDetails = userServiceImpl.loadUserByUsername(username);
//    	if(userDetails != null) {
//    		return true;
//    	}else {
//    		return false;
//    	}
//		
//        // ...
//    }
//}
















