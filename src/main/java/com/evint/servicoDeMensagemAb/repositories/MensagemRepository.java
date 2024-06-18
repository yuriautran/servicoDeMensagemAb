package com.evint.servicoDeMensagemAb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.evint.servicoDeMensagemAb.entities.Mensagem;

@Repository
public interface MensagemRepository extends JpaRepository<Mensagem, Long> {

}
