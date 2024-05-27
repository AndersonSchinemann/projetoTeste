package com.evision.controller;


import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("dashboard")
public class DashboardController {



	private static final Pattern HTML_TAG_PATTERN = Pattern.compile("<[^>]*>");

    public static String convertHtmlToText(String html) {
        Matcher matcher = HTML_TAG_PATTERN.matcher(html);
        return matcher.replaceAll("");
    }
	
    @GetMapping
    public ModelAndView index(Model model) {	
		//model.addAttribute("usuario", usuario);
		ModelAndView mv = new ModelAndView();
	    mv.setViewName("/templates/dashboard");
	    return mv;
	}
    @GetMapping("/detalhe")
    @ResponseBody
    public ResponseEntity<String> exibirDetalheusuario() throws IOException {
    	 String html = "<title>Detalhe Usuario</title> <div class=\\\"container text-left\\\" ng-app=\\\"ListarApp\\\" ng-controller=\\\"UsuarioController\\\">";
         String plainText = convertHtmlToText(html);
         return ResponseEntity.ok().body("{\"htmlContent\": \"" + plainText + "\"}");
    }
    @GetMapping("/listagem")
    public ResponseEntity<String> listagem()throws IOException {
    	String texto = "<button type=\\\"button\\\" class=\\\"btn btn-primary\\\" ng-click=\\\"atualizarUsuario()\\\">Atualizar</button>";
    	return ResponseEntity.ok().body("{\"texto\": \"" + texto + "\"}");
    }
}