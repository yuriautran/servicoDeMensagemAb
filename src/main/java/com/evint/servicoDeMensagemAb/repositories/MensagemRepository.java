package com.evint.servicoDeMensagemAb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.evint.servicoDeMensagemAb.entities.Mensagem;

public interface MensagemRepository extends JpaRepository<Mensagem, Long> {

}
