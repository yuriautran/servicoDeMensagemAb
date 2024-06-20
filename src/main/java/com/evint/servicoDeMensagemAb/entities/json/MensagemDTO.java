package com.evint.servicoDeMensagemAb.entities.json;

import java.time.Instant;
import java.util.Objects;

import com.evint.servicoDeMensagemAb.entities.Mensagem;
import com.evint.servicoDeMensagemAb.entities.UsuarioMensagem;

public class MensagemDTO {
	
	private Long id;
	private String titulo;
	private String descricao;
	private Instant dataEntrega;
	private Instant dataLeitura;
	
	public MensagemDTO() {
	}

	public MensagemDTO(Long id, String titulo, String descricao, Instant dataEntrega, Instant dataLeitura) {
		this.id = id;
		this.titulo = titulo;
		this.descricao = descricao;
		this.dataEntrega = dataEntrega;
		this.dataLeitura = dataLeitura;
	}
	
	public MensagemDTO(Mensagem msg, UsuarioMensagem um) {
		id = msg.getId();
		titulo = msg.getTitulo();
		descricao = msg.getDescricao();
		dataEntrega = um.getDataEntrega();
		dataLeitura = um.getDataLeitura();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Instant getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(Instant dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public Instant getDataLeitura() {
		return dataLeitura;
	}

	public void setDataLeitura(Instant dataLeitura) {
		this.dataLeitura = dataLeitura;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MensagemDTO other = (MensagemDTO) obj;
		return Objects.equals(id, other.id);
	}
}
