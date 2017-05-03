package br.com.arguments.manager;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

import br.com.arguments.dto.GruposDTO;
import br.com.arguments.entity.CursosEntity;
import br.com.arguments.entity.GruposEntity;
import br.com.arguments.entity.LoginEntity;
import br.com.arguments.service.GruposService;
import br.com.arguments.util.jsf.SessionUtil;

@ManagedBean
@ViewScoped
public class GruposManager implements Serializable {

	private static final Logger LOG = Logger.getLogger(GruposEntity.class.getName());

	private static final String SUCESSO_01 = "SUCESSO";

	@EJB
	private GruposService gruposService;

	private List<GruposEntity> listaGrupos;

	private GruposDTO gruposDTO;

	private LoginEntity user;

	private boolean edit;

	private GruposEntity gruposEntity;

	private int cursoSelecionado;

	private List<CursosEntity> listaCursos;
	
	private int tipoGrupo;
	
	/** TESTE **/
	private MapModel emptyModel;
	
    private String title;
    
    private double lat;
      
    private double lng;

	@PostConstruct
	public void init() {
		user = (LoginEntity) SessionUtil.getParam("UserLoged");
		carregaListaCurso();
		posInit();
	}

	private void posInit() {
		gruposDTO = new GruposDTO();
		edit = false;
	}

	public void cadastrarGrupos() {
		System.out.println("hi");
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

	public int getCursoSelecionado() {
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

	public void setCursoSelecionado(int cursoSelecionado) {
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

	public int getTipoGrupo() {
		return tipoGrupo;
	}

	public void setTipoGrupo(int tipoGrupo) {
		this.tipoGrupo = tipoGrupo;
	}

}
