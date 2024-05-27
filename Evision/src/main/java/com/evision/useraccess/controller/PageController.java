package com.evision.useraccess.controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.evision.useraccess.service.AuthenticationService;

//responsavel pelo carregamento de paginas sem necessidade de autenticação
@Controller 
public class PageController {
	
	private final ResourceLoader resourceLoader;
	public AuthenticationService authenticationService;

    public PageController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
   
    @GetMapping("/login")
    public ModelAndView index(Model model) {	
		ModelAndView mv = new ModelAndView();
	    mv.setViewName("/access/templates/loginUser");
	    return mv;
	}
    
    
    @GetMapping("/listagem_")
    public ModelAndView profileTeste(Model model) {	
		ModelAndView mv = new ModelAndView();
	    mv.setViewName("/access/templates/listagem");
	    return mv;
	}
    

    @GetMapping("/cadastro_")
    @ResponseBody
    public String exibirFormularioCadastro() throws IOException {
    	//System.out.print("cadastro form -----------------." + "\n");
        Resource resource = resourceLoader.getResource("classpath:static/access/templates/cadastro.html");
        InputStream inputStream = resource.getInputStream();
        String htmlContent = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        return htmlContent;
    }
    @GetMapping("/detalhe_")
    public ResponseEntity<String> exibirDetalheusuario() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:static/templates/detalhe.html");
        InputStream inputStream = resource.getInputStream();
        String htmlContent = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        return ResponseEntity.ok().body("{\"htmlContent\": \"" + htmlContent + "\"}");
    }
    
   
}