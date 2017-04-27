package br.com.arguments.manager;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

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
	
	private static final Logger LOG = Logger.getLogger(CadastroManager.class.getName());
	
	private static final String ERRO_01 = "ERRO";
	
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
		if ((!dto.getNome().isEmpty() && dto.getNome() != null)) {
			if(validData()){
				if(cursoSelecionado != null){
					for(CursosEntity item : listaCursos){
						if(item.getId().equals(new Long(cursoSelecionado))){
							dto.setCurso(item);
						}
					}
					
					event = eventoService.insert(dto);
					
					timeLineService.insertEvent(event,user);
					
					posInit();
					carregaLista();
					context.addMessage(null, new FacesMessage("Sucesso", "Cadastrado com Sucesso"));
				}else{
					LOG.warning(ERRO_01 + " Nenhum curso selecionado! ");
					context.addMessage(null, new FacesMessage(ERRO_01, "Nenhum curso selecionado!"));
				}
			}else{
				LOG.warning(ERRO_01 + " Nenhum curso selecionado! "); //data errada
				context.addMessage(null, new FacesMessage(ERRO_01, "Nenhum curso selecionado!"));
			}
		} else {
			LOG.warning(ERRO_01 + " Campos Sem preencher! ");
			context.addMessage(null, new FacesMessage(ERRO_01, "Campos Sem preencher!"));
		}
	}
	
	private boolean validData(){
		if(dto.getDataInicio() != null){
			try{
			    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm");
			    Date parsedDate = dateFormat.parse(dto.getDataInicio());
			    Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
			    dto.setDataInicioStamp(timestamp);
			    return true;
			}catch(Exception e){//this generic but you can control another types of exception
				e.printStackTrace();
				return false;
			}
		}else{
			return false;
		}
		
	}
	
	public String removeEvent() {
		eventoService.remove(selectedEvent);
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage("Sucesso", "AOISDJAIOSDJOAIsj"));
		return "evento.xhtml?faces-redirect=true";
	}

	public void editEvent(EventoEntity entity) {
		if (entity != null) {
			dto = new EventoDTO();
			dto.setId(entity.getId());
			dto.setNome(entity.getNome());
			dto.setDescricao(entity.getDescricao());
			dto.setDataCriacao(entity.getDataCriacao());
			cursoSelecionado = entity.getNumCurso().getId().intValue();
			dto.setDataInicio(convertoCompleteTimestampToString(entity.getDataInicio()));
			dto.setAtivo(entity.isAtivo());
			edit = true;
		} else {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("ERRO", "Evento em branco"));
		}
	}

	public String convertDateToString(Date data) {
		if (data != null) {
			SimpleDateFormat formatado = new SimpleDateFormat("dd/MM/yyyy");
			return formatado.format(data);
		}
		return null;
	}
	
	public String convertTimestampToString(Timestamp data){
		return new SimpleDateFormat("dd/MM/yyyy").format(data);
	}
	
	private String convertoCompleteTimestampToString(Timestamp data){
		return new SimpleDateFormat("dd/MM/yyyy hh:mm").format(data);
	}
	
	public void selecionaEvento(EventoEntity evento){
		this.selectedEvent = evento;
	}

	public void saveEventoEdit() {
//		FacesContext context = FacesContext.getCurrentInstance();
//		EventoEntity event = new EventoEntity();
//		if ((!dto.getNome().isEmpty() && dto.getNome() != null)) {
//			if(validData()){
//				if(cursoSelecionado != null){
//					for(CursosEntity item : listaCursos){
//						if(item.getId().equals(new Long(cursoSelecionado))){
//							dto.setCurso(item);
//						}
//					}
//					
//					event = eventoService.insert(dto);
//					
//					timeLineService.insertEvent(event);
//					
//					posInit();
//					carregaLista();
//					context.addMessage(null, new FacesMessage("Sucesso", "Cadastrado com Sucesso"));
//				}else{
//					LOG.warning(ERRO_01 + " Nenhum curso selecionado! ");
//					context.addMessage(null, new FacesMessage(ERRO_01, "Nenhum curso selecionado!"));
//				}
//			}else{
//				LOG.warning(ERRO_01 + " Nenhum curso selecionado! "); //data errada
//				context.addMessage(null, new FacesMessage(ERRO_01, "Nenhum curso selecionado!"));
//			}
//		} else {
//			LOG.warning(ERRO_01 + " Campos Sem preencher! ");
//			context.addMessage(null, new FacesMessage(ERRO_01, "Campos Sem preencher!"));
//		}
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
