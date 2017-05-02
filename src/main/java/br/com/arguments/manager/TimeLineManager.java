package br.com.arguments.manager;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.arguments.entity.DebateCursoEntity;
import br.com.arguments.entity.DebateEntity;
import br.com.arguments.entity.LoginEntity;
import br.com.arguments.entity.TimeLineEntity;
import br.com.arguments.entity.TipoConteudoDebateEntity;
import br.com.arguments.entity.TipoConteudoEventoEntity;
import br.com.arguments.entity.UsuarioEntity;
import br.com.arguments.filter.TimeLineFilter;
import br.com.arguments.service.DebateService;
import br.com.arguments.service.TimeLineService;
import br.com.arguments.util.jsf.SessionUtil;

@ManagedBean
@ViewScoped
public class TimeLineManager implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private TimeLineService timeLineService;
	
	@EJB
	private DebateService debateService;
	
	private LoginEntity login;

	private UsuarioEntity user;
	
	private List<TimeLineEntity> timeLineLista;
	
	private List<TimeLineEntity> timeLineListaFinal;
	
	private List<TimeLineFilter> listaFilter;
	
	private List<TipoConteudoEventoEntity> tipoConteudoEventoLista;
	
	private List<Integer> listaIdConteudoEvento;
	
	@PostConstruct
	public void init() {
		login = (LoginEntity) SessionUtil.getParam("UserLoged");
		user = login.getIdUsuario();
		timeLineLista = timeLineService.findTimeLineByCurso(user.getIdInstituicaoCursos().getIdCursosEntity().getId().intValue());
		
		listaIdConteudoEvento = new ArrayList<>();
		
		HashMap<String, TimeLineFilter> map = new HashMap<>();
		
		String key = null;
		
		List<TipoConteudoEventoEntity> evento = new ArrayList<>(); 
		List<TipoConteudoDebateEntity> debate = new ArrayList<>();
		TimeLineFilter time = null;
		
		if(timeLineLista != null){
			timeLineListaFinal = new ArrayList<>();
			updateList();
			for(TimeLineEntity item : timeLineListaFinal){
				
				key = new SimpleDateFormat("dd/MM/yyyy").format(item.getDataCriacao());
				
				time = new TimeLineFilter();
				
				if(map.containsKey(key)){
					
					if(item.getTipoConteudo() == 1){
						if(map.get(key).getListaEvento() == null){
							map.get(key).setListaEvento(new ArrayList<>());
						}
						map.get(key).getListaEvento().add(item.getIdTipoConteudoEvento());
					}else if(item.getTipoConteudo() == 2){
						if(map.get(key).getListaDebate() == null){
							map.get(key).setListaDebate(new ArrayList<>());
						}
						map.get(key).getListaDebate().add(item.getIdTipoConteudoDebate());
					}
					
				}else{
					
					if(item.getTipoConteudo() == 1){
						evento = new ArrayList<>();
						evento.add(item.getIdTipoConteudoEvento());
						time.setListaEvento(evento);
					}else if(item.getTipoConteudo() == 2){
						debate = new ArrayList<>();
						debate.add(item.getIdTipoConteudoDebate());
						time.setListaDebate(debate);
					}
					
					time.setData(item.getDataCriacao());
					
					map.put(key, time);
				}
			}
			
			listaFilter = new ArrayList<TimeLineFilter>(map.values());
			
			Collections.sort(listaFilter, new Comparator<TimeLineFilter>() {
				@Override
				public int compare(TimeLineFilter o1, TimeLineFilter o2) {
					if(o1.getData() != null && o2.getData() != null){
						return o1.getData().compareTo(o2.getData());
					}else{
						return 0;
					}
				}
			});
			
		}
		
		
	}
	
	private void updateList(){
		
		for(int x = 0; x < timeLineLista.size(); x++){
			if(timeLineLista.get(x).getIdTipoConteudoDebate() != null){
				if(!timeLineLista.get(x).getIdTipoConteudoDebate().getDataFechamento().before(dataAtual()) ||
						timeLineLista.get(x).getIdTipoConteudoDebate().getDataFechamento().equals(dataAtual())){
					timeLineListaFinal.add(timeLineLista.get(x));
				}
			}
			if(timeLineLista.get(x).getIdTipoConteudoEvento() != null){
				timeLineListaFinal.add(timeLineLista.get(x));
			}
				
		}
	}
	
	public Timestamp dataAtual(){
		try{
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			String dataAtual = format.format(new Date());
		    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		    Date parsedDate = dateFormat.parse(dataAtual.toString());
		    Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
		    return timestamp;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public Long qtdComentariosDebate(DebateEntity id){
		
		List<DebateCursoEntity> ls = new ArrayList<>(debateService.findAllDebatesByDebate(id));
		
		return ls != null ? ls.size() : new Long(0);
	}
	
	public String comentarDebate(DebateEntity debate){
		SessionUtil.setParam("DebateSelecionado", debate);
		return "debateDetalhe.xhtml?faces-redirect=true";
	}
	
	public String convertDateToString(Date data) {
		if (data != null) {
			SimpleDateFormat formatado = new SimpleDateFormat("dd/MM/yyyy");
			return formatado.format(data);
		}
		return null;
	}
	
	public String convertDateToStringHora(Date data) {
		if (data != null) {
			SimpleDateFormat formatado = new SimpleDateFormat("HH:mm");
			return formatado.format(data);
		}
		return null;
	}
	
	public String convertDateToStringFull(Date data) {
		if (data != null) {
			SimpleDateFormat formatado = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			return formatado.format(data);
		}
		return null;
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

	public List<TimeLineFilter> getListaFilter() {
		return listaFilter;
	}

	public void setListaFilter(List<TimeLineFilter> listaFilter) {
		this.listaFilter = listaFilter;
	}

	public List<TimeLineEntity> getTimeLineListaFinal() {
		return timeLineListaFinal;
	}

	public void setTimeLineListaFinal(List<TimeLineEntity> timeLineListaFinal) {
		this.timeLineListaFinal = timeLineListaFinal;
	}

}
