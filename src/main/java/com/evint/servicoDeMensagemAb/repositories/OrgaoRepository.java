package com.evint.servicoDeMensagemAb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.evint.servicoDeMensagemAb.entities.Orgao;

public interface OrgaoRepository extends JpaRepository<Orgao, Long> {
	
	Orgao findByNomeIgnoreCase(String nome);

}
