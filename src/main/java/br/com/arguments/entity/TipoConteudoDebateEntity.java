package br.com.arguments.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="TIPO_CONTEUDO_DEBATE")
public class TipoConteudoDebateEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_TIPO_CONTEUDO_DEBATE")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="NOME_DEBATE")
	private String nome;
	
	@Column(name="TEMA_DEBATE")
	private String tema;
	
	@ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="ID_INSTITUICAO_CURSOS")
	private InstituicaoCursosEntity idInstituicaoCursos;
	
	@Temporal(TemporalType.DATE)
	@Column(name="DATA_ABERTURA")
	private Date data_abertura;
	
	@Temporal(TemporalType.DATE)
	@Column(name="DATA_FECHAMENTO")
	private Date data_fechamento;
	
	@Column(name="STATUS") // 1-Criado 2-Finalizado
	private int status;
	
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

	public String getTema() {
		return tema;
	}

	public void setTema(String tema) {
		this.tema = tema;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getData_abertura() {
		return data_abertura;
	}

	public void setData_abertura(Date data_abertura) {
		this.data_abertura = data_abertura;
	}

	public Date getData_fechamento() {
		return data_fechamento;
	}

	public void setData_fechamento(Date data_fechamento) {
		this.data_fechamento = data_fechamento;
	}

	public InstituicaoCursosEntity getIdInstituicaoCursos() {
		return idInstituicaoCursos;
	}

	public void setIdInstituicaoCursos(InstituicaoCursosEntity idInstituicaoCursos) {
		this.idInstituicaoCursos = idInstituicaoCursos;
	}

}
