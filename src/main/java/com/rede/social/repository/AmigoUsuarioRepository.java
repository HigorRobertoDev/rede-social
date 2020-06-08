package com.rede.social.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rede.social.model.AmigoUsuario;

@Repository
public interface AmigoUsuarioRepository extends JpaRepository<AmigoUsuario, Long> {
	List<AmigoUsuario> findAllByUsuarioId(Long id);
}
