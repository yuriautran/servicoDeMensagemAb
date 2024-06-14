package com.evint.servicoDeMensagemAb.entities.pk;

import java.io.Serializable;
import java.util.Objects;

import com.evint.servicoDeMensagemAb.entities.Mensagem;
import com.evint.servicoDeMensagemAb.entities.Usuario;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
public class UsuarioMensagemPK implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name = "mensagem_id")
	private Mensagem mensagem;
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Mensagem getMensgem() {
		return mensagem;
	}
	public void setMensgem(Mensagem mensgem) {
		this.mensagem = mensgem;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(mensagem, usuario);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UsuarioMensagemPK other = (UsuarioMensagemPK) obj;
		return Objects.equals(mensagem, other.mensagem) && Objects.equals(usuario, other.usuario);
	}
}
