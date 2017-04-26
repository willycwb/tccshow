package br.com.arguments.manager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.arguments.dto.DebateDTO;
import br.com.arguments.entity.CursosEntity;
import br.com.arguments.entity.DebateEntity;
import br.com.arguments.entity.InstituicaoCursosEntity;
import br.com.arguments.entity.InstituicaoEntity;
import br.com.arguments.entity.LoginEntity;
import br.com.arguments.service.DebateService;
import br.com.arguments.service.InstituicaoService;
import br.com.arguments.service.TimeLineService;
import br.com.arguments.util.jsf.SessionUtil;

@ManagedBean
@ViewScoped
public class DebateManager implements Serializable {
	
	private static final Logger LOG = Logger.getLogger(DebateManager.class.getName());
	
	private static final String ERRO_01 = "ERRO";
	
	private static final String ERRO_02 = "ERRO INESPERADO";
	
	private static final String SUCESSO_01 = "SUCESSO";
	
	@EJB
	private DebateService debateService;
	
	@EJB
	private TimeLineService timeLineService;
	
	@EJB
	private InstituicaoService instituicaoService;
	
	private List<DebateEntity> listaDebate;
	
	private List<CursosEntity> listaCursos;
	
	private Integer cursoSelecionado;
	
	private DebateDTO debateDTO;
	
	private LoginEntity user;
	
	private boolean edit;
	
	DebateEntity debate;
	
	private Long selectedarea;
	
	private Long selectedclassif;
	
	private List<InstituicaoCursosEntity> listaInstituicaoCursos;
	
	private Integer instituicaoSelecionada;
	
	private List<InstituicaoEntity> listaInstituicao;
	
	private boolean verifica;
	
	@PostConstruct
	public void init(){		
		user = (LoginEntity) SessionUtil.getParam("UserLoged");
		listaInstituicao = instituicaoService.findAllInstituicao();
		posInit();
	}
	
	private void posInit(){
		debateDTO = new DebateDTO();
		edit = false;
	}
	
	public void CriaDebate(){
		FacesContext context = FacesContext.getCurrentInstance();
		DebateEntity debate = new DebateEntity();
		
		if((!debateDTO.getNomeDebate().isEmpty() && debateDTO.getNomeDebate() != null) || debateDTO.getDataCriacao() != null
				|| debateDTO.getDataFechamento() != null){
			debate = debateService.insert(debateDTO);
			
			timeLineService.insertDebate(debate);
			
			posInit();
			carregaLista();
			buscaCursoInstutuicao();
			context.addMessage(null, new FacesMessage(SUCESSO_01, "Cadastro com Sucesso"));
		}
	}


	
	private void carregaLista(){
		listaDebate = debateService.findAllDebates();		
	}
	
	public void editaDebate(DebateEntity debatentidade){
		if(debatentidade != null){
			debateDTO = new DebateDTO();
			debateDTO.setId(Long.valueOf(debatentidade.getId()));
			debateDTO.setNomeDebate(debatentidade.getNome());
			debateDTO.setTemaDebate(debatentidade.getTema());
			debateDTO.setCurso(debatentidade.getCurso());
			debateDTO.setIdInstituicaoCursos(debatentidade.getIdInstituicaoCursos());
			debateDTO.setDataCriacao(debatentidade.getData_abertura().toString());
			debateDTO.setDataFechamento(debatentidade.getData_fechamento().toString());
			edit = true;
		}else{
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("ERRO", "Debate está em branco"));
		}
	}
	
	public void buscaCursosInstituicao(){
		for(InstituicaoEntity item : listaInstituicao){
			if(item.getId() == instituicaoSelecionada.longValue()){
				listaInstituicaoCursos = instituicaoService.findCursosByInstituicao(item);
				if(listaInstituicaoCursos != null){
					listaCursos = new ArrayList<>();
					for(InstituicaoCursosEntity it : listaInstituicaoCursos){
						listaCursos.add(it.getIdCursosEntity());
					}
				}
			}
		}
	}
	
	public void buscaCursoInstutuicao(){
		for(InstituicaoCursosEntity item : listaInstituicaoCursos){
			if(item.getIdCursosEntity().getId() == cursoSelecionado.longValue() &&
					item.getIdInstituicaoEntity().getId() == instituicaoSelecionada.longValue()){
				debateDTO.setIdInstituicaoCursos(item);
			}
		}
	}
	

	public DebateService getDebateService() {
		return debateService;
	}

	public void setDebateService(DebateService debateService) {
		this.debateService = debateService;
	}

	public List<DebateEntity> getListaDebate() {
		if (listaDebate == null) {
			carregaLista();
		}
		return listaDebate;
	}

	public void setListaDebate(List<DebateEntity> listaDebate) {
		this.listaDebate = listaDebate;
	}

	public DebateDTO getDebateDTO() {
		return debateDTO;
	}

	public void setDebateDTO(DebateDTO debateDTO) {
		this.debateDTO = debateDTO;
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

	public Long getSelectedarea() {
		return selectedarea;
	}

	public void setSelectedarea(Long selectedarea) {
		this.selectedarea = selectedarea;
	}

	public Long getSelectedclassif() {
		return selectedclassif;
	}

	public void setSelectedclassif(Long selectedclassif) {
		this.selectedclassif = selectedclassif;
	}

	public boolean isVerifica() {
		return verifica;
	}

	public void setVerifica(boolean verifica) {
		this.verifica = verifica;
	}

	public List<CursosEntity> getListaCursos() {
		return listaCursos;
	}

	public Integer getCursoSelecionado() {
		return cursoSelecionado;
	}

	public void setCursoSelecionado(Integer cursoSelecionado) {
		this.cursoSelecionado = cursoSelecionado;
	}

	public void setListaCursos(List<CursosEntity> listaCursos) {
		this.listaCursos = listaCursos;
	}

	public List<InstituicaoCursosEntity> getListaInstituicaoCursos() {
		return listaInstituicaoCursos;
	}

	public void setListaInstituicaoCursos(List<InstituicaoCursosEntity> listaInstituicaoCursos) {
		this.listaInstituicaoCursos = listaInstituicaoCursos;
	}

	public Integer getInstituicaoSelecionada() {
		return instituicaoSelecionada;
	}

	public void setInstituicaoSelecionada(Integer instituicaoSelecionada) {
		this.instituicaoSelecionada = instituicaoSelecionada;
	}

	public List<InstituicaoEntity> getListaInstituicao() {
		return listaInstituicao;
	}

	public void setListaInstituicao(List<InstituicaoEntity> listaInstituicao) {
		this.listaInstituicao = listaInstituicao;
	}

}
