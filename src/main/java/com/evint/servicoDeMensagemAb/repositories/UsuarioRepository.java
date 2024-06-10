package com.evint.servicoDeMensagemAb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.evint.servicoDeMensagemAb.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
