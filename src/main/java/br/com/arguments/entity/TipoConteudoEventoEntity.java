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

@Entity
@Table(name="TIPO_CONTEUDO_EVENTO")
public class TipoConteudoEventoEntity implements Serializable {

	@Id
	@Column(name="ID_TIPO_CONTEUDO_EVENTO")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="NOME_EVENTO")
	private String nome;
	
	@Column(name="DESCRICAO", nullable=true)
	private String descricao;
	
    @Column(name = "DATA_EVENTO")
    private Timestamp dataInicio;
	
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

	public Date getDataInicio() {
		return dataInicio;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setDataInicio(Timestamp dataInicio) {
		this.dataInicio = dataInicio;
	}

}
