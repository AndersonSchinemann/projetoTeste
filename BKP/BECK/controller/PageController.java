package com.evision.controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.evision.service.AuthenticationService;


@Controller //responsavel pelo carregamento de paginas sem necessidade de autenticação
//@RestController
public class PageController {
	
	private final ResourceLoader resourceLoader;
	public AuthenticationService authenticationService;
    //@Autowired
    public PageController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
   
    
//    @GetMapping("/login")
//   // @ResponseBody
//    public String exibirFormularioLogin() throws IOException {
//        Resource resource = resourceLoader.getResource("classpath:static/views/loginUser.html");
//        InputStream inputStream = resource.getInputStream();
//        String htmlContent = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
//        return htmlContent;
//    }
    
    @GetMapping("/login")
    public ModelAndView index(Model model) {	
		//model.addAttribute("usuario", usuario);
		ModelAndView mv = new ModelAndView();
	    mv.setViewName("/templates/loginUser");
	    return mv;
	}
    @GetMapping("/listagem")
    public ModelAndView profileTeste(Model model) {	
		//model.addAttribute("usuario", usuario);
		ModelAndView mv = new ModelAndView();
	    mv.setViewName("/templates/listagem");
	    return mv;
	}
    
    //@RequestMapping(value = "/cadastro", method = RequestMethod.GET)
    @GetMapping("/cadastro")
    @ResponseBody
    public String exibirFormularioCadastro() throws IOException {
    	//System.out.print("cadastro form -----------------." + "\n");
        Resource resource = resourceLoader.getResource("classpath:static/templates/cadastro.html");
        InputStream inputStream = resource.getInputStream();
        String htmlContent = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        return htmlContent;
    }
    @GetMapping("/detalhe")
    public ResponseEntity<String> exibirDetalheusuario() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:static/templates/detalhe.html");
        InputStream inputStream = resource.getInputStream();
        String htmlContent = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        return ResponseEntity.ok().body("{\"htmlContent\": \"" + htmlContent + "\"}");
    }
    
   
    
  
//   
   
}