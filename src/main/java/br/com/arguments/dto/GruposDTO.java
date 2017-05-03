package br.com.arguments.dto;

import java.util.Date;

import br.com.arguments.entity.CursosEntity;

public class GruposDTO {

	private Long id;

	private String nomeGrupo;

	private CursosEntity curso;

	private String dataInicial;

	private Date data;

	private int qtdMaximaMembros;

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

	public CursosEntity getCurso() {
		return curso;
	}

	public int getQtdMaximaMembros() {
		return qtdMaximaMembros;
	}

	public void setCurso(CursosEntity curso) {
		this.curso = curso;
	}

	public void setQtdMaximaMembros(int qtdMaximaMembros) {
		this.qtdMaximaMembros = qtdMaximaMembros;
	}

	public String getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(String dataInicial) {
		this.dataInicial = dataInicial;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

}