package br.com.arguments.dto;

import br.com.arguments.entity.UsuarioEntity;

public class GruposDTO {
	
	private Long id;
	
	private String nomeGrupo;
	
	private String privacidade;
	
	private String membros;
	
	private String curso;
	
	private UsuarioEntity usuario;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeGrupo() {
		return nomeGrupo;
	}

	public void setNomeGrupo(String nomeGrupo) {
		this.nomeGrupo = nomeGrupo;
	}

	public String getPrivacidade() {
		return privacidade;
	}

	public void setPrivacidade(String privacidade) {
		this.privacidade = privacidade;
	}

	public String getMembros() {
		return membros;
	}

	public void setMembros(String membros) {
		this.membros = membros;
	}

	public UsuarioEntity getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioEntity usuario) {
		this.usuario = usuario;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}
	
}
