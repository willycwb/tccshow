package br.com.arguments.manager;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.arguments.dto.GruposDTO;
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
	
	GruposEntity gruposEntity;
	
	private boolean verifica;
	
	@PostConstruct
	public void init(){		
		user = (LoginEntity) SessionUtil.getParam("UserLoged");
		posInit();
	}
	
	private void posInit(){
		gruposDTO = new GruposDTO();
		edit = false;
	}
	
	public void criaGrupo(){
		FacesContext context = FacesContext.getCurrentInstance();
		GruposEntity grupos = new GruposEntity();
		
		if(!gruposDTO.getNomeGrupo().isEmpty() && gruposDTO.getNomeGrupo() != null){
			grupos = gruposService.insert(gruposDTO);
			
			posInit();
			carregaLista();
			context.addMessage(null, new FacesMessage(SUCESSO_01, "Cadastro com Sucesso"));
		}
	}
	
	private void carregaLista(){
		listaGrupos = gruposService.findAllGrupos();
	}

	public GruposService getGruposService() {
		return gruposService;
	}

	public void setGruposService(GruposService gruposService) {
		this.gruposService = gruposService;
	}

	public List<GruposEntity> getListaGrupos() {
		if (listaGrupos == null) {
			carregaLista();
		}
		return listaGrupos;
	}

	public void setListaGrupos(List<GruposEntity> listaGrupos) {
		this.listaGrupos = listaGrupos;
	}

	public GruposDTO getGruposDTO() {
		return gruposDTO;
	}

	public void setGruposDTO(GruposDTO gruposDTO) {
		this.gruposDTO = gruposDTO;
	}

	public LoginEntity getUser() {
		return user;
	}

	public void setUser(LoginEntity user) {
		this.user = user;
	}

	public boolean isEdit() {
		return edit;
	}

	public void setEdit(boolean edit) {
		this.edit = edit;
	}

	public boolean isVerifica() {
		return verifica;
	}

	public void setVerifica(boolean verifica) {
		this.verifica = verifica;
	}

}
