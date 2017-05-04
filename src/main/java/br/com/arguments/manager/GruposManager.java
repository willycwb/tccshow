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

import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

import br.com.arguments.dto.GruposDTO;
import br.com.arguments.entity.CursosEntity;
import br.com.arguments.entity.EventoEntity;
import br.com.arguments.entity.GruposEntity;
import br.com.arguments.entity.LoginEntity;
import br.com.arguments.service.GruposService;
import br.com.arguments.util.jsf.SessionUtil;

@ManagedBean
@ViewScoped
public class GruposManager implements Serializable {

	private static final Logger LOG = Logger.getLogger(GruposEntity.class.getName());

	private static final String SUCESSO_01 = "SUCESSO";
	
	private static final String ERRO_01 = "ERRO";

	@EJB
	private GruposService gruposService;

	private List<GruposEntity> listaGrupos;

	private GruposDTO gruposDTO;

	private LoginEntity user;

	private boolean edit;

	private GruposEntity gruposEntity;

	private Integer cursoSelecionado;

	private List<CursosEntity> listaCursos;
	
	/** TESTE **/
	private MapModel emptyModel;
	
    private String title;
    
    private double lat;
      
    private double lng;

	@PostConstruct
	public void init() {
		user = (LoginEntity) SessionUtil.getParam("UserLoged");
		cursoSelecionado = null;
		carregaListaCurso();
		carregaListaGrupos();
		posInit();
	}

	private void posInit() {
		gruposDTO = new GruposDTO();
		edit = false;
	}

	public void cadastrarGrupos() {
		System.out.println("hi");
		FacesContext context = FacesContext.getCurrentInstance();
		GruposEntity grupos = new GruposEntity();
		if ((!gruposDTO.getNomeGrupo().isEmpty() && gruposDTO.getNomeGrupo() != null)) {
			if (validData()) {
				if (cursoSelecionado != null) {
					for (CursosEntity item : listaCursos) {
						if (item.getId().equals(new Long(cursoSelecionado))) {
							gruposDTO.setCurso(item);
						}
					}
					
					grupos = gruposService.insert(gruposDTO,user);

//					timeLineService.insertEvent(event, user);

					posInit();
					carregaListaCurso();
					context.addMessage(null, new FacesMessage("Sucesso", "Cadastrado com Sucesso"));
				} else {
					LOG.warning(ERRO_01 + " Nenhum curso selecionado! ");
					context.addMessage(null, new FacesMessage(ERRO_01, "Nenhum curso selecionado!"));
				}
			} else {
				LOG.warning(ERRO_01 + " Erro de data! "); 
				context.addMessage(null, new FacesMessage(ERRO_01, "Data errada!"));
			}
		} else {
			LOG.warning(ERRO_01 + " Campos Sem preencher! ");
			context.addMessage(null, new FacesMessage(ERRO_01, "Campos Sem preencher!"));
		}
	}
	
	private boolean validData() {
		if (gruposDTO.getDataInicial() != null) {
			try {
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm");
				Date parsedDate = dateFormat.parse(gruposDTO.getDataInicial());
				Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
				gruposDTO.setDataInicialStamp(timestamp);
				return true;
			} catch (Exception e) {// this generic but you can control another
									// types of exception
				e.printStackTrace();
				return false;
			}
		} else {
			return false;
		}

	}

	public void addMarker() {
		emptyModel = new DefaultMapModel();
		Marker marker = new Marker(new LatLng(lat, lng), title);
		emptyModel.addOverlay(marker);

//		FacesContext.getCurrentInstance().addMessage(null,
//				new FacesMessage(FacesMessage.SEVERITY_INFO, "Marker Added", "Lat:" + lat + ", Lng:" + lng));
	}
	
	private void carregaListaCurso(){
		listaCursos = gruposService.findAllCursos();
	}
	
	private void carregaListaGrupos(){
//		listaCursos = gruposService.findAllCursos();
		listaGrupos = gruposService.findAllGrupos();
	}

	public List<GruposEntity> getListaGrupos() {
		return listaGrupos;
	}

	public GruposDTO getGruposDTO() {
		return gruposDTO;
	}

	public LoginEntity getUser() {
		return user;
	}

	public boolean isEdit() {
		return edit;
	}

	public GruposEntity getGruposEntity() {
		return gruposEntity;
	}

	public Integer getCursoSelecionado() {
		return cursoSelecionado;
	}

	public List<CursosEntity> getListaCursos() {
		return listaCursos;
	}

	public void setListaGrupos(List<GruposEntity> listaGrupos) {
		this.listaGrupos = listaGrupos;
	}

	public void setGruposDTO(GruposDTO gruposDTO) {
		this.gruposDTO = gruposDTO;
	}

	public void setUser(LoginEntity user) {
		this.user = user;
	}

	public void setEdit(boolean edit) {
		this.edit = edit;
	}

	public void setGruposEntity(GruposEntity gruposEntity) {
		this.gruposEntity = gruposEntity;
	}

	public void setCursoSelecionado(Integer cursoSelecionado) {
		this.cursoSelecionado = cursoSelecionado;
	}

	public void setListaCursos(List<CursosEntity> listaCursos) {
		this.listaCursos = listaCursos;
	}

	public MapModel getEmptyModel() {
		return emptyModel;
	}

	public String getTitle() {
		return title;
	}

	public double getLat() {
		return lat;
	}

	public double getLng() {
		return lng;
	}

	public void setEmptyModel(MapModel emptyModel) {
		this.emptyModel = emptyModel;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

}
