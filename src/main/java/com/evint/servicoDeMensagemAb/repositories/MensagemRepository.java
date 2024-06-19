package com.evint.servicoDeMensagemAb.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.evint.servicoDeMensagemAb.entities.Mensagem;

@Repository
public interface MensagemRepository extends JpaRepository<Mensagem, Long> {

	@Query(value = "SELECT * FROM MENSAGEM m"
            + " JOIN USUARIO_MENSAGEM um ON m.ID = um.MENSAGEM_ID"
            + " WHERE um.USUARIO_ID = :id AND um.DATA_LEITURA IS NULL", nativeQuery = true)
	List<Mensagem> buscarMensagensNaoLidas(Long id);	
}
