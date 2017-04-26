package br.com.arguments.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="EVENTOS")
public class EventoEntity implements Serializable {

	@Id
	@Column(name="ID_EVENTO")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="NOME_EVENTO")
	private String nome;
	
	@Column(name="DESCRICAO", nullable=true)
	private String descricao;
	
	@Column(name="NUM_IMAGEM", nullable=true)
	private int numImagem;
	
	@Column(name="NUM_CURSO", nullable=true)
	private CursosEntity numCurso;
	
	@Column(name="IMAGEM", nullable=true)
	private boolean imagem;
	
	@Column(name="ATIVO")
	private boolean ativo;
	
    @Column(name = "DATA_EVENTO")
    private Timestamp dataInicio;
	
	@Temporal(TemporalType.DATE)
    @Column(name = "DATA_CRIACAO")
    private Date dataCriacao;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Timestamp getDataInicio() {
		return dataInicio;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public boolean isImagem() {
		return imagem;
	}

	public void setImagem(boolean imagem) {
		this.imagem = imagem;
	}

	public int getNumImagem() {
		return numImagem;
	}

	public void setNumImagem(int numImagem) {
		this.numImagem = numImagem;
	}

	public CursosEntity getNumCurso() {
		return numCurso;
	}

	public void setNumCurso(CursosEntity numCurso) {
		this.numCurso = numCurso;
	}

	public void setDataInicio(Timestamp dataInicio) {
		this.dataInicio = dataInicio;
	}

}
