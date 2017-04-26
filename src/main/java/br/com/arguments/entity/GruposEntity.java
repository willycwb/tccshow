package br.com.arguments.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="GRUPOS")
public class GruposEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_GRUPO")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="NOME_GRUPO")
	private String nome;
	
	@Column(name="CURSO")
	private String curso;	
	
	@Column(name="PRIVACIDADE")
	private String privacidade;
	
	@Column(name="MEMBROS")
	private String membros;
	
	@Column(name="ID_USUARIO")
	private UsuarioEntity idUsuarioEntity;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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

	public UsuarioEntity getIdUsuarioEntity() {
		return idUsuarioEntity;
	}

	public void setIdUsuarioEntity(UsuarioEntity idUsuarioEntity) {
		this.idUsuarioEntity = idUsuarioEntity;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}
	
	
}
