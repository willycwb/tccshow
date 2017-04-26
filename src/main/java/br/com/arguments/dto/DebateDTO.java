package br.com.arguments.dto;

import br.com.arguments.entity.CursosEntity;
import br.com.arguments.entity.InstituicaoCursosEntity;
import br.com.arguments.entity.UsuarioEntity;

public class DebateDTO {
	
	private Long id;
	
	private String nomeDebate;
	
	private String temaDebate;
	
	private CursosEntity curso;
	
	private InstituicaoCursosEntity idInstituicaoCursos;
	
	private int status; // 1-ativo 0-terminado
	
	private String dataCriacao;
	
	private String dataFechamento;
	
	private UsuarioEntity usuario;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeDebate() {
		return nomeDebate;
	}

	public void setNomeDebate(String nomeDebate) {
		this.nomeDebate = nomeDebate;
	}

	public String getTemaDebate() {
		return temaDebate;
	}

	public void setTemaDebate(String temaDebate) {
		this.temaDebate = temaDebate;
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

	public String getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(String dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public String getDataFechamento() {
		return dataFechamento;
	}

	public void setDataFechamento(String dataFechamento) {
		this.dataFechamento = dataFechamento;
	}

	public CursosEntity getCurso() {
		return curso;
	}

	public void setCurso(CursosEntity curso) {
		this.curso = curso;
	}

	public InstituicaoCursosEntity getIdInstituicaoCursos() {
		return idInstituicaoCursos;
	}

	public void setIdInstituicaoCursos(InstituicaoCursosEntity idInstituicaoCursos) {
		this.idInstituicaoCursos = idInstituicaoCursos;
	}
	

}
