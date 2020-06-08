package com.rede.social;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import com.rede.social.dto.UsuarioDTO;
import com.rede.social.dto.UsuarioVinculoAmigoDTO;
import com.rede.social.model.Usuario;
import com.rede.social.service.UsuarioService;

@SpringBootApplication
public class RedeSocialApplication {
	
	@Autowired
	UsuarioService usuarioService;
	
	public static void main(String[] args) {
		SpringApplication.run(RedeSocialApplication.class, args);
	}
	
	@EventListener(ApplicationReadyEvent.class)
	public void doSomethingAfterStartup() throws Exception {
	    
		// Adicionar usuários
		List<String> usuarios = new ArrayList<String>();
		usuarios.add("Priscila");
		usuarios.add("Higor");
		usuarios.add("Emanuel");
		usuarios.add("Valentina");
		usuarios.add("Maria");
		usuarios.add("Jose");
		usuarios.add("Aline");
		usuarios.add("Wellington");
		usuarios.add("Melissa");
		
		usuarios.forEach(u -> {
			UsuarioDTO dto = new UsuarioDTO();
			dto.setNome(u);
			try {
				usuarioService.save(dto);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		
		// Relacionar usuários
		UsuarioVinculoAmigoDTO amigoDTO = new UsuarioVinculoAmigoDTO();
		amigoDTO.setNome("Priscila");
		amigoDTO.setNomeAmigo("Higor");
		usuarioService.addAmigo(amigoDTO);
		
		amigoDTO.setNome("Priscila");
		amigoDTO.setNomeAmigo("Emanuel");
		usuarioService.addAmigo(amigoDTO);
		
		amigoDTO.setNome("Priscila");
		amigoDTO.setNomeAmigo("Valentina");
		usuarioService.addAmigo(amigoDTO);
		
		amigoDTO.setNome("Priscila");
		amigoDTO.setNomeAmigo("Maria");
		usuarioService.addAmigo(amigoDTO);
		
		amigoDTO.setNome("Higor");
		amigoDTO.setNomeAmigo("Melissa");
		usuarioService.addAmigo(amigoDTO);
		
		amigoDTO.setNome("Higor");
		amigoDTO.setNomeAmigo("Wellington");
		usuarioService.addAmigo(amigoDTO);
		
		amigoDTO.setNome("Higor");
		amigoDTO.setNomeAmigo("Jose");
		usuarioService.addAmigo(amigoDTO);
		
		amigoDTO.setNome("Higor");
		amigoDTO.setNomeAmigo("Maria");
		usuarioService.addAmigo(amigoDTO);
		
		amigoDTO.setNome("Emanuel");
		amigoDTO.setNomeAmigo("Maria");
		usuarioService.addAmigo(amigoDTO);
		
		amigoDTO.setNome("Emanuel");
		amigoDTO.setNomeAmigo("Valentina");
		usuarioService.addAmigo(amigoDTO);
		
		amigoDTO.setNome("Valentina");
		amigoDTO.setNomeAmigo("Higor");
		usuarioService.addAmigo(amigoDTO);
		
		amigoDTO.setNome("Maria");
		amigoDTO.setNomeAmigo("Higor");
		usuarioService.addAmigo(amigoDTO);
	}
	
}
