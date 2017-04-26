package br.com.arguments.manager;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.arguments.entity.LoginEntity;
import br.com.arguments.entity.TimeLineEntity;
import br.com.arguments.entity.TipoConteudoDebateEntity;
import br.com.arguments.entity.TipoConteudoEventoEntity;
import br.com.arguments.entity.UsuarioEntity;
import br.com.arguments.filter.TimeLineFilter;
import br.com.arguments.service.TimeLineService;
import br.com.arguments.util.jsf.SessionUtil;

@ManagedBean
@ViewScoped
public class TimeLineManager implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private TimeLineService timeLineService;
	
	private LoginEntity login;

	private UsuarioEntity user;
	
	private List<TimeLineEntity> timeLineLista;
	
	private List<TipoConteudoEventoEntity> tipoConteudoEventoLista;
	
	private List<Integer> listaIdConteudoEvento;
	
	@PostConstruct
	public void init() {
		login = (LoginEntity) SessionUtil.getParam("UserLoged");
		user = login.getIdUsuario();
		timeLineLista = timeLineService.findTimeLineByCurso(user.getIdInstituicaoCursos().getIdCursosEntity().getId().intValue());
		
		listaIdConteudoEvento = new ArrayList<>();
		
		List<TimeLineFilter> filter = new ArrayList<>();
		
		HashMap<Timestamp, List<TimeLineFilter>> map = new HashMap<>();
		
		Timestamp key = null;
		
		List<TipoConteudoEventoEntity> evento = null;
		List<TipoConteudoDebateEntity> debate = null;
		TimeLineFilter time = null;
		
		for(TimeLineEntity item : timeLineLista){
			key = item.getDataCriacao();
			
			if(map.containsKey(key)){
				
				

				
				
			}else{
				
				if(item.getTipoConteudo() == 1){
					evento = new ArrayList<>();
					evento.add(item.getIdTipoConteudoEvento());
				}else if(item.getTipoConteudo() == 2){
					debate = new ArrayList<>();
					debate.add(item.getIdTipoConteudoDebate());
				}
				
				time = new TimeLineFilter();
				
				time.setData(item.getDataCriacao());
				time.setListaDebate(debate);
				time.setListaEvento(evento);
				
				map.put(key, filter);
			}
		}
//		
//		if(listaIdConteudoEvento.size() > 0){
//			consultaTipoConteudoEvento();
//		}
		
	}
	
	/** GETTERS E SETTERS */

	public LoginEntity getLogin() {
		return login;
	}

	public void setLogin(LoginEntity login) {
		this.login = login;
	}

	public UsuarioEntity getUser() {
		return user;
	}

	public void setUser(UsuarioEntity user) {
		this.user = user;
	}

	public List<TimeLineEntity> getTimeLineLista() {
		return timeLineLista;
	}

	public void setTimeLineLista(List<TimeLineEntity> timeLineLista) {
		this.timeLineLista = timeLineLista;
	}

	public List<TipoConteudoEventoEntity> getTipoConteudoEventoLista() {
		return tipoConteudoEventoLista;
	}

	public void setTipoConteudoEventoLista(List<TipoConteudoEventoEntity> tipoConteudoEventoLista) {
		this.tipoConteudoEventoLista = tipoConteudoEventoLista;
	}

	public List<Integer> getListaIdConteudoEvento() {
		return listaIdConteudoEvento;
	}

	public void setListaIdConteudoEvento(List<Integer> listaIdConteudoEvento) {
		this.listaIdConteudoEvento = listaIdConteudoEvento;
	}

}
