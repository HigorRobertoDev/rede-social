package com.rede.social.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rede.social.dto.UsuarioDTO;
import com.rede.social.dto.UsuarioVinculoAmigoDTO;
import com.rede.social.model.AmigoUsuario;
import com.rede.social.model.Usuario;
import com.rede.social.repository.AmigoUsuarioRepository;
import com.rede.social.repository.UsuarioRepository;
import com.rede.social.representation.AmigosDosAmigosRepresentaion;
import com.rede.social.representation.AmigosUsuarioRepresentation;
import com.rede.social.representation.UsuarioRepresentation;
import com.rede.social.representation.UsuariosRepresentation;

import javassist.NotFoundException;

@Service
public class UsuarioService {
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	AmigoUsuarioRepository amigoUsuarioRepository;
	
	public UsuarioRepresentation save(UsuarioDTO usuarioDTO) throws Exception {
		if (usuarioDTO.getNome() != null) {
			if (usuarioRepository.findByNome(usuarioDTO.getNome()).isPresent())
				throw new Exception("Nome já existe: " + usuarioDTO.getNome());
		}
		
		return from(usuarioRepository.save(from(usuarioDTO)));
	}
	
	public AmigosUsuarioRepresentation addAmigo(UsuarioVinculoAmigoDTO vinculoDTO) throws NotFoundException {
		Optional<Usuario> usuario = Optional.ofNullable(usuarioRepository.findByNome(vinculoDTO.getNome())
                .orElseThrow(() -> new NotFoundException("Nome não existe: " + vinculoDTO.getNome())));
		
		Optional<Usuario> usuarioAmigo = Optional.ofNullable(usuarioRepository.findByNome(vinculoDTO.getNomeAmigo())
                .orElseThrow(() -> new NotFoundException("Nome do amigo não existe: " + vinculoDTO.getNomeAmigo())));
		
		AmigoUsuario amigo = new AmigoUsuario();
		amigo.setUsuario(usuario.get());
		amigo.setAmigo(usuarioAmigo.get());
		
		amigoUsuarioRepository.save(amigo);
		return fromRepresentarion(usuario.get());
	}
	
	public AmigosUsuarioRepresentation buscarAmigosDoUsuarioPorNome(UsuarioDTO usuarioDTO) throws NotFoundException {
		Optional<Usuario> usuario = Optional.ofNullable(usuarioRepository.findByNome(usuarioDTO.getNome())
                .orElseThrow(() -> new NotFoundException("Nome não existe: " + usuarioDTO.getNome())));
		
		return fromRepresentarion(usuario.get());
	}
	
	public AmigosDosAmigosRepresentaion buscarAmigosDosAmigosPorNome(UsuarioDTO usuarioDTO) throws NotFoundException {
		Optional<Usuario> usuario = Optional.ofNullable(usuarioRepository.findByNome(usuarioDTO.getNome())
                .orElseThrow(() -> new NotFoundException("Nome não existe: " + usuarioDTO.getNome())));
		
		return fromRepresentation(usuario.get());
	}
	
	public UsuariosRepresentation buscarUsuarios() {
		List<Usuario> usuarios = usuarioRepository.findAll();
		return from(usuarios);
	}
	
	public UsuariosRepresentation from(List<Usuario> usuarios) {
		UsuariosRepresentation representation = new UsuariosRepresentation();
		
		List<UsuarioRepresentation> usuarioRepresentations = new ArrayList<>();
		usuarios.stream().forEach(u -> {
			UsuarioRepresentation usuarioRepresentation = new UsuarioRepresentation();
			usuarioRepresentation.setId(u.getId());
			usuarioRepresentation.setNome(u.getNome());
			usuarioRepresentations.add(usuarioRepresentation);
		});
		
		representation.setUsuarios(usuarioRepresentations);
		return representation;
	}
	
	
	public Usuario from(UsuarioDTO dto) {
		Usuario usuario = new Usuario();
		usuario.setNome(dto.getNome());
		return usuario;
	}
	
	public UsuarioRepresentation from(Usuario usuario) {
		UsuarioRepresentation representation = new UsuarioRepresentation();
		representation.setId(usuario.getId());
		representation.setNome(usuario.getNome());
		return representation;
	}
	
	public Usuario from(Optional<Usuario> opUsuario) {
		Usuario usuario = new Usuario();
		usuario.setId(opUsuario.get().getId());
		usuario.setNome(opUsuario.get().getNome());
		return usuario;
	}
	
	public AmigosUsuarioRepresentation fromRepresentarion(Usuario usuario) {
		List<UsuarioRepresentation> amigosRepresentations = new ArrayList<UsuarioRepresentation>();
				
		List<AmigoUsuario> amigosUsuario = amigoUsuarioRepository.findAllByUsuarioId(usuario.getId());
		amigosUsuario.stream().forEach(l -> {
			UsuarioRepresentation representation = new UsuarioRepresentation();
			representation.setId(l.getAmigo().getId());
			representation.setNome(l.getAmigo().getNome());
			amigosRepresentations.add(representation);
		});
		
		AmigosUsuarioRepresentation representation = new AmigosUsuarioRepresentation();
		representation.setUsuario(from(usuario));
		representation.setAmigos(amigosRepresentations);
		
		return representation;
	}
	
	public AmigosDosAmigosRepresentaion fromRepresentation(Usuario usuario) {
		UsuarioRepresentation usuarioRepresentation = new UsuarioRepresentation();
		usuarioRepresentation.setId(usuario.getId());
		usuarioRepresentation.setNome(usuario.getNome());
		
		List<AmigosUsuarioRepresentation> amigosUsuarioRepresentations = new ArrayList<>();
		
		List<AmigoUsuario> amigosUsuario = amigoUsuarioRepository.findAllByUsuarioId(usuario.getId());
		amigosUsuario.stream().forEach(l -> {
			UsuarioRepresentation amigo = new UsuarioRepresentation();
			amigo.setId(l.getAmigo().getId());
			amigo.setNome(l.getAmigo().getNome());
			
			List<UsuarioRepresentation> amigosDosAmigoRepresentations = new ArrayList<>();
			List<AmigoUsuario> amigosDosAmigo = amigoUsuarioRepository.findAllByUsuarioId(amigo.getId());
			amigosDosAmigo.stream().forEach(a -> {
				UsuarioRepresentation amigoDoAmigo = new UsuarioRepresentation();
				amigoDoAmigo.setId(a.getAmigo().getId());
				amigoDoAmigo.setNome(a.getAmigo().getNome());
				
				amigosDosAmigoRepresentations.add(amigoDoAmigo);
			});
			
			AmigosUsuarioRepresentation amigoDoAmigoRepresentation = new AmigosUsuarioRepresentation();
			amigoDoAmigoRepresentation.setUsuario(amigo);
			amigoDoAmigoRepresentation.setAmigos(amigosDosAmigoRepresentations);
			
			amigosUsuarioRepresentations.add(amigoDoAmigoRepresentation);
		});
		
		AmigosDosAmigosRepresentaion amigosDosAmigosRepresentaion = new AmigosDosAmigosRepresentaion();
		amigosDosAmigosRepresentaion.setUsuario(usuarioRepresentation);
		amigosDosAmigosRepresentaion.setAmigos(amigosUsuarioRepresentations);
		
		return amigosDosAmigosRepresentaion;
	}
	
}
