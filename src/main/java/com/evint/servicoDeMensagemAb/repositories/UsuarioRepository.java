package com.evint.servicoDeMensagemAb.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import com.evint.servicoDeMensagemAb.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>, QueryByExampleExecutor<Usuario> {

	List<Usuario> findByUfIgnoreCase(String uf);
}
