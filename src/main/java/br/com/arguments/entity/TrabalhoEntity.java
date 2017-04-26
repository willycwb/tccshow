package br.com.arguments.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TRABALHOS")
public class TrabalhoEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_TRABALHO")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="NOME_TRABALHO")
	private String nome;
	
	@Column(name="CURSO")
	private String curso;
	
	@Column(name="COMENTARIO")
	private String comentario;
	
	@Column(name="CAMINHO")
	private String caminho;
	
	@Column(name="NOME_ARQUIVO")
	private String nomearq;
	
	@Column(name="STATUS") // 1-publicado(alunos podem ver) 2-postado(alunos nao podem ver) -0 rejeitado
	private int status;
	
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

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public UsuarioEntity getIdUsuarioEntity() {
		return idUsuarioEntity;
	}

	public void setIdUsuarioEntity(UsuarioEntity idUsuarioEntity) {
		this.idUsuarioEntity = idUsuarioEntity;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public String getCaminho() {
		return caminho;
	}

	public void setCaminho(String caminho) {
		this.caminho = caminho;
	}

	public String getNomearq() {
		return nomearq;
	}

	public void setNomearq(String nomearq) {
		this.nomearq = nomearq;
	}

}
