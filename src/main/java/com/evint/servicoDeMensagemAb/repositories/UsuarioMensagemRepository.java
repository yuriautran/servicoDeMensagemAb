package com.evint.servicoDeMensagemAb.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.evint.servicoDeMensagemAb.entities.UsuarioMensagem;
import com.evint.servicoDeMensagemAb.entities.pk.UsuarioMensagemPK;

@Repository
public interface UsuarioMensagemRepository extends JpaRepository<UsuarioMensagem, UsuarioMensagemPK> {

	@Query(value = "SELECT um.USUARIO_ID, um.MENSAGEM_ID, UM.DATA_ENTREGA, um.DATA_LEITURA, um.DATA_EXCLUSAO"
			+ " FROM USUARIO_MENSAGEM um"
            + " JOIN MENSAGEM m ON m.ID = um.MENSAGEM_ID"
            + " JOIN USUARIO u ON u.ID = um.USUARIO_ID"
            + " WHERE u.CPF = :cpf"
            + " AND um.DATA_EXCLUSAO IS NULL", nativeQuery = true)
    List<UsuarioMensagem> retornarUsuarioMensagensSemDataExclusao(String cpf);
}
