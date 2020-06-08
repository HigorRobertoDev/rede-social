package com.rede.social.representation;

import java.util.List;

public class AmigosUsuarioRepresentation {
	
	private UsuarioRepresentation usuario;
	private List<UsuarioRepresentation> amigos;
	
	public UsuarioRepresentation getUsuario() {
		return usuario;
	}
	
	public void setUsuario(UsuarioRepresentation usuario) {
		this.usuario = usuario;
	}
	
	public List<UsuarioRepresentation> getAmigos() {
		return amigos;
	}
	
	public void setAmigos(List<UsuarioRepresentation> amigos) {
		this.amigos = amigos;
	}

}
