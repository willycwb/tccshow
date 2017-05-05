package br.com.arguments.filter;

import java.sql.Timestamp;
import java.util.List;

import br.com.arguments.entity.TipoConteudoDebateEntity;
import br.com.arguments.entity.TipoConteudoEventoEntity;
import br.com.arguments.entity.TipoConteudoGrupoEntity;

public class TimeLineFilter {
	
	private Timestamp data;
	
	private List<TipoConteudoEventoEntity> listaEvento;
	
	private List<TipoConteudoDebateEntity> listaDebate;
	
	private List<TipoConteudoGrupoEntity> listaGrupo;

	public Timestamp getData() {
		return data;
	}

	public void setData(Timestamp data) {
		this.data = data;
	}

	public List<TipoConteudoEventoEntity> getListaEvento() {
		return listaEvento;
	}

	public void setListaEvento(List<TipoConteudoEventoEntity> listaEvento) {
		this.listaEvento = listaEvento;
	}

	public List<TipoConteudoDebateEntity> getListaDebate() {
		return listaDebate;
	}

	public void setListaDebate(List<TipoConteudoDebateEntity> listaDebate) {
		this.listaDebate = listaDebate;
	}

	public List<TipoConteudoGrupoEntity> getListaGrupo() {
		return listaGrupo;
	}

	public void setListaGrupo(List<TipoConteudoGrupoEntity> listaGrupo) {
		this.listaGrupo = listaGrupo;
	}

}
