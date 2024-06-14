package com.evint.servicoDeMensagemAb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.evint.servicoDeMensagemAb.entities.UsuarioMensagem;
import com.evint.servicoDeMensagemAb.entities.pk.UsuarioMensagemPK;

@Repository
public interface UsuarioMensagemRepository extends JpaRepository<UsuarioMensagem, UsuarioMensagemPK> {

}
