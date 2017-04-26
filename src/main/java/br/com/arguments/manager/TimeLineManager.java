package br.com.arguments.manager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.arguments.entity.LoginEntity;
import br.com.arguments.entity.TimeLineEntity;
import br.com.arguments.entity.TipoConteudoEventoEntity;
import br.com.arguments.entity.UsuarioEntity;
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
		
//		for(TimeLineEntity item : timeLineLista){
//			if(item.getTipoConteudo() == 1){
//				listaIdConteudoEvento.add(item.getIdTipoConteudo());
//			}
//		}
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
