package com.evint.servicoDeMensagemAb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.evint.servicoDeMensagemAb.entities.Orgao;

@Repository
public interface OrgaoRepository extends JpaRepository<Orgao, Long> {
	
	Orgao findByNomeIgnoreCase(String nome);

}
