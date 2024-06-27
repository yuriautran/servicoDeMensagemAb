package com.evint.servicoDeMensagemAb.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import com.evint.servicoDeMensagemAb.entities.pk.UsuarioMensagemPK;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuario_Mensagem")
public class UsuarioMensagem implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private UsuarioMensagemPK id = new UsuarioMensagemPK(); //Atributo identificador (criado através de uma classe auxiliar "UsuarioMensagemPK
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
	private Instant dataEntrega;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
	private Instant dataLeitura;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
	private Instant dataExclusao;

	public UsuarioMensagem() {
		
	}

	public UsuarioMensagem(Usuario usuario, Mensagem mensagem, Instant dataEntrega, Instant dataLeitura, Instant dataExclusao) {
		super();
		id.setUsuario(usuario);
		id.setMensgem(mensagem);
		this.dataEntrega = dataEntrega;
		this.dataLeitura = dataLeitura;
		this.dataExclusao = dataExclusao;
	}
	
	public Usuario getUsuario() {
		return id.getUsuario();
	}

	public void Usuario(Usuario usuario) {
		id.setUsuario(usuario);
	}
	
	public Mensagem getMensagem() {
		return id.getMensgem();
	}
	
	public void setMensagem(Mensagem mensagem) {
		id.setMensgem(mensagem);
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

	public Instant getDataExclusao() {
		return dataExclusao;
	}

	public void setDataExclusao(Instant dataExclusao) {
		this.dataExclusao = dataExclusao;
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
		UsuarioMensagem other = (UsuarioMensagem) obj;
		return Objects.equals(id, other.id);
	}
}
