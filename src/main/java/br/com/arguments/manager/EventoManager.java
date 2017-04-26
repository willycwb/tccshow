package br.com.arguments.manager;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.arguments.dto.EventoDTO;
import br.com.arguments.entity.CursosEntity;
import br.com.arguments.entity.EventoEntity;
import br.com.arguments.entity.LoginEntity;
import br.com.arguments.entity.UsuarioEntity;
import br.com.arguments.service.EventoService;
import br.com.arguments.service.TimeLineService;
import br.com.arguments.util.jsf.SessionUtil;

@ManagedBean
@ViewScoped
public class EventoManager implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private EventoService eventoService;
	
	@EJB
	private TimeLineService timeLineService;
	
	private List<EventoEntity> listEvento;

	private EventoEntity selectedEvent;

	private EventoDTO dto;

	private boolean edit;

	private LoginEntity login;

	private UsuarioEntity user;
	
	private List<CursosEntity> listaCursos;
	
	private Integer cursoSelecionado;

	@PostConstruct
	public void init() {
		login = (LoginEntity) SessionUtil.getParam("UserLoged");
		user = login.getIdUsuario();
		listaCursos = buscaListaCursos();
		posInit();
	}
	
	private void posInit(){
		dto = new EventoDTO();
		edit = false;
	}
	
	private List<CursosEntity> buscaListaCursos(){
		return eventoService.findAllCursos();
	}

	public void cadastrarEvento() {
		FacesContext context = FacesContext.getCurrentInstance();
		EventoEntity event = new EventoEntity();
		if ((!dto.getNome().isEmpty() && dto.getNome() != null) || dto.getDataInicio() != null) {
			
			for(CursosEntity item : listaCursos){
				if(item.getId().equals(new Long(cursoSelecionado))){
					dto.setCurso(item);
				}
			}
			
			event = eventoService.insert(dto);
			
			timeLineService.insertEvent(event);
			
			posInit();
			carregaLista();
			context.addMessage(null, new FacesMessage("que ota", "Cadastrado com Sucesso"));
		} else {
//			LOG.warning(ERRO_01 + " Campos Sem preencher! ");
//			context.addMessage(null, new FacesMessage(ERRO_01, "Campos Sem preencher!"));
		}
	}
	
	public String removeEvent() {
//		eventoService.remove(selectedEvent);
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage("Sucesso", "AOISDJAIOSDJOAIsj"));
		return "evento.xhtml?faces-redirect=true";
	}

	public void editEvent(EventoEntity entity) {
//		if (entity != null) {
//			dto = new EventoDTO();
//			dto.setId(Long.valueOf(entity.getId()));
//			dto.setNome(entity.getNome());
//			dto.setDataCriacao(entity.getDataCriacao());
////			dto.setDataFinal(convertDateToString(entity.getDataFinal()));
//			dto.setDataInicio(convertDateToString(entity.getDataInicio()));
//			dto.setAtivo(entity.isAtivo());
//			edit = true;
//			tabindex = new Long("0");
//		} else {
//			FacesContext context = FacesContext.getCurrentInstance();
//			context.addMessage(null, new FacesMessage("ERRO", "Evento em branco"));
//		}
	}

	public String convertDateToString(Date data) {
		if (data != null) {
			SimpleDateFormat formatado = new SimpleDateFormat("dd/MM/yyyy");
			return formatado.format(data);
		}
		return null;
	}

	public String saveEdit() {
//		if (edit) {
//			FacesContext context = FacesContext.getCurrentInstance();
//			if ((!dto.getNome().isEmpty() && dto.getNome() != null) || dto.getDataInicio() != null
//					|| dto.getDataFinal() != null) {
////				eventoService.update(dto);
//				context.addMessage(null, new FacesMessage(ERRO_01, "Cadastrado com Sucesso"));
//			} else {
//				LOG.warning(ERRO_01 + " Campos Sem preencher! ");
//				context.addMessage(null, new FacesMessage(ERRO_01, "Campos Sem preencher!"));
//				return null;
//			}
//		}
		return "evento.xhtml?faces-redirect=true";
	}
	
	private void carregaLista() {
		listEvento = eventoService.findAllActive();
	}

	/** GETTERS E SETTERS */

	public EventoEntity getSelectedEvent() {
		return selectedEvent;
	}

	public void setSelectedEvent(EventoEntity selectedEvent) {
		this.selectedEvent = selectedEvent;
	}

	public List<EventoEntity> getListEvento() {
		if (listEvento == null) {
			carregaLista();
		}
		return listEvento;
	}

	public void setListEvento(List<EventoEntity> listEvento) {
		this.listEvento = listEvento;
	}

	public EventoDTO getDto() {
		return dto;
	}

	public void setDto(EventoDTO dto) {
		this.dto = dto;
	}

	public boolean isEdit() {
		return edit;
	}

	public void setEdit(boolean edit) {
		this.edit = edit;
	}

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

	public List<CursosEntity> getListaCursos() {
		return listaCursos;
	}

	public void setListaCursos(List<CursosEntity> listaCursos) {
		this.listaCursos = listaCursos;
	}

	public Integer getCursoSelecionado() {
		return cursoSelecionado;
	}

	public void setCursoSelecionado(Integer cursoSelecionado) {
		this.cursoSelecionado = cursoSelecionado;
	}

}
