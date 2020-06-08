package com.rede.social.representation;

import java.util.List;

public class AmigosDosAmigosRepresentaion {
	
	private UsuarioRepresentation usuario;
	private List<AmigosUsuarioRepresentation> amigos;
	
	public UsuarioRepresentation getUsuario() {
		return usuario;
	}
	
	public void setUsuario(UsuarioRepresentation usuario) {
		this.usuario = usuario;
	}
	
	public List<AmigosUsuarioRepresentation> getAmigos() {
		return amigos;
	}
	
	public void setAmigos(List<AmigosUsuarioRepresentation> amigos) {
		this.amigos = amigos;
	}

}
