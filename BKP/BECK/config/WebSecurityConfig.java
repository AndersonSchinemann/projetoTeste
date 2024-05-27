package com.evision.config;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.evision.service.UserDetailsServiceImpl;
import com.evision.service.UsuarioService;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig {


	
	@Value("${jwt.public.key}")
	private RSAPublicKey key;
	@Value("${jwt.private.key}")
	private RSAPrivateKey priv;



	@Bean
	@Order(1)
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	    http.csrf(CsrfConfigurer::disable)  // Desabilita CSRF (opcional)
	        .authorizeHttpRequests(requests -> requests
	            .requestMatchers("/src/**", "/static/**", "/css/**", "/views/**", "/js/**","/img/**").permitAll()
	            .requestMatchers("/cadastro").permitAll()  // Permita acesso ao cadastro 
	            .requestMatchers("/process").permitAll()
	            .requestMatchers("/js/controllerLogin.js").permitAll()
	            .anyRequest().authenticated()) // Exige autenticação para outras rotas
	        .formLogin(form -> form  // Configure o login por formulário
	            .loginPage("/login")
	            //.loginProcessingUrl("/process")
	            .defaultSuccessUrl("/dashboard",true)
	            .permitAll())
	        //.httpBasic(Customizer.withDefaults())
	        .oauth2ResourceServer(
	                conf -> conf.jwt(
	                    jwt -> jwt.decoder(jwtDecoder())));
	        // Habilita autenticação básica
	    return http.build();
	}

	@Bean
	JwtDecoder jwtDecoder() {
		return NimbusJwtDecoder.withPublicKey(this.key).build();
	}

	@Bean
	JwtEncoder jwtEncoder() {
		JWK jwk = new RSAKey.Builder(this.key).privateKey(this.priv).build();
		JWKSource<SecurityContext> jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
		return new NimbusJwtEncoder(jwks);
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration().applyPermitDefaultValues();
		configuration.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "DELETE", "OPTIONS"));
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
//

//

//	@Bean
//	AuthenticationManager authenticationManager(HttpSecurity http,PasswordEncoder passwordEncoder,
//	                                            UserDetailsServiceImpl userDetailsService) throws Exception {
//
//	    // Configure AuthenticationManagerBuilder directly
//	    AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
//	    authenticationManagerBuilder.userDetailsService(userService)
//	                               .passwordEncoder(passwordEncoder);
//
//	    return authenticationManagerBuilder.build();
//	}


//	
//    
	@Bean
	BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
//    
//	 @Bean
//	 LogoutFilter logoutFilter() {
//	     LogoutFilter logoutFilter = new LogoutFilter(
//	         "/logout",
//	         new SecurityContextLogoutHandler());
//	     logoutFilter.setFilterProcessesUrl("/logout");
//	     return logoutFilter;
//	 }

}