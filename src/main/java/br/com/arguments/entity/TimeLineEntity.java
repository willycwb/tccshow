package br.com.arguments.entity;

import java.io.Serializable;
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
@Table(name="TIME_LINE")
public class TimeLineEntity implements Serializable {

	@Id
	@Column(name="ID_TIME_LINE")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="TIPO_CONTEUDO")
	private int tipoConteudo;
	
	@Column(name="ID_TIPO_CONTEUDO_EVENTO")
	private TipoConteudoEventoEntity idTipoConteudoEvento;
	
	@Column(name="ID_TIPO_CONTEUDO_DEBATE")
	private TipoConteudoDebateEntity idTipoConteudoDebate;
	
	@Column(name="ID_CURSO", nullable=true)
	private int idCurso;
	
	@Temporal(TemporalType.DATE)
    @Column(name = "DATA_CRIACAO")
    private Date dataCriacao;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTipoConteudo() {
		return tipoConteudo;
	}

	public void setTipoConteudo(int tipoConteudo) {
		this.tipoConteudo = tipoConteudo;
	}

	public int getIdCurso() {
		return idCurso;
	}

	public void setIdCurso(int idCurso) {
		this.idCurso = idCurso;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public TipoConteudoEventoEntity getIdTipoConteudoEvento() {
		return idTipoConteudoEvento;
	}

	public void setIdTipoConteudoEvento(TipoConteudoEventoEntity idTipoConteudoEvento) {
		this.idTipoConteudoEvento = idTipoConteudoEvento;
	}

	public TipoConteudoDebateEntity getIdTipoConteudoDebate() {
		return idTipoConteudoDebate;
	}

	public void setIdTipoConteudoDebate(TipoConteudoDebateEntity idTipoConteudoDebate) {
		this.idTipoConteudoDebate = idTipoConteudoDebate;
	}
	
}
