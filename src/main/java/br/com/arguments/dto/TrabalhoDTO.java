package br.com.arguments.dto;

import br.com.arguments.entity.UsuarioEntity;

public class TrabalhoDTO {
	
	private Long id;
	
	private String nomeTrabalho;
	
	private String curso;
	
	private String caminho;
	
	private String  nomearquivo;
	
	private String comentario;
	
	private int status; // 1-publicado(alunos podem ver) 2-postado(alunos nao podem ver) -0 rejeitado
	
	private UsuarioEntity usuario;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeTrabalho() {
		return nomeTrabalho;
	}

	public void setNomeTrabalho(String nomeTrabalho) {
		this.nomeTrabalho = nomeTrabalho;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public UsuarioEntity getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioEntity usuario) {
		this.usuario = usuario;
	}

	public String getCaminho() {
		return caminho;
	}

	public void setCaminho(String caminho) {
		this.caminho = caminho;
	}

	public String getNomearquivo() {
		return nomearquivo;
	}

	public void setNomearquivo(String nomearquivo) {
		this.nomearquivo = nomearquivo;
	}

}
