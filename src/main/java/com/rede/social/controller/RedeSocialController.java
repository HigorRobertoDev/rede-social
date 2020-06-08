package com.rede.social.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rede.social.dto.UsuarioDTO;
import com.rede.social.dto.UsuarioVinculoAmigoDTO;
import com.rede.social.representation.AmigosDosAmigosRepresentaion;
import com.rede.social.representation.AmigosUsuarioRepresentation;
import com.rede.social.representation.UsuarioRepresentation;
import com.rede.social.representation.UsuariosRepresentation;
import com.rede.social.service.UsuarioService;

import javassist.NotFoundException;

@RestController
@RequestMapping("/rede-social")
public class RedeSocialController {

	@Autowired
	UsuarioService usuarioService;
	
	@PostMapping("/usuario/salvar")
	@CrossOrigin
	public ResponseEntity<UsuarioRepresentation> salvarUsuario(@RequestBody UsuarioDTO input) throws Exception {
		return new ResponseEntity<>(
				usuarioService.save(input),
				HttpStatus.CREATED
		);
	}
	
	@PostMapping("/usuario/vincular/amigo")
	@CrossOrigin
	public ResponseEntity<AmigosUsuarioRepresentation> vincularAmigo(@RequestBody UsuarioVinculoAmigoDTO input) throws NotFoundException {
		return new ResponseEntity<>(
				usuarioService.addAmigo(input),
				HttpStatus.CREATED
		);
	}
	
	@PostMapping("/usuario/buscar/amigo")
	@CrossOrigin
	public ResponseEntity<AmigosUsuarioRepresentation> buscarAmigosDoUsuarioPorNome(@RequestBody UsuarioDTO input) throws NotFoundException {
		return new ResponseEntity<>(
				usuarioService.buscarAmigosDoUsuarioPorNome(input),
				HttpStatus.OK
		);
	}
	
	@PostMapping("/usuario/buscar/amigos/amigo")
	@CrossOrigin
	public ResponseEntity<AmigosDosAmigosRepresentaion> buscarAmigosDosAmigoPorNome(@RequestBody UsuarioDTO input) throws NotFoundException {
		return new ResponseEntity<>(
				usuarioService.buscarAmigosDosAmigosPorNome(input),
				HttpStatus.OK
		);
	}
	
	@GetMapping("/usuarios")
	@CrossOrigin
	public ResponseEntity<UsuariosRepresentation> buscarUsuarios() {
		return new ResponseEntity<>(
				usuarioService.buscarUsuarios(),
				HttpStatus.OK
		);
	}
	
}
