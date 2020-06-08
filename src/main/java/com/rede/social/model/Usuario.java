package com.rede.social.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;



@Entity(name = "usuario")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	
	@OneToMany(mappedBy = "usuario")
	@JsonManagedReference
	private List<AmigoUsuario> usuarios;
	
	@OneToMany(mappedBy = "amigo")
	@JsonManagedReference
	private List<AmigoUsuario> amigos;

	public Usuario() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<AmigoUsuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<AmigoUsuario> usuarios) {
		this.usuarios = usuarios;
	}

	public List<AmigoUsuario> getAmigos() {
		return amigos;
	}

	public void setAmigos(List<AmigoUsuario> amigos) {
		this.amigos = amigos;
	}
	
	
	
}
