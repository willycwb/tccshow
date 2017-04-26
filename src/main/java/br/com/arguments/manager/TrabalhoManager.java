package br.com.arguments.manager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.UploadedFile;



import br.com.arguments.dto.TrabalhoDTO;
import br.com.arguments.entity.LoginEntity;
import br.com.arguments.entity.TrabalhoEntity;
import br.com.arguments.service.TrabalhoService;
import br.com.arguments.util.jsf.SessionUtil;

@ManagedBean
@ViewScoped
public class TrabalhoManager implements Serializable{
	
	private static final String SUCESSO_01 = "SUCESSO";
	
	private static final Logger LOG = Logger.getLogger(TrabalhoManager.class.getName());
	
	private static final String ERRO_01 = "ERRO";
	
	@EJB
	private TrabalhoService trabalhoService;
	
	private List<TrabalhoEntity> listaTrabalho;
	
	private TrabalhoDTO trabalhoDTO;
	
	private LoginEntity user;
	
	private boolean edit;
	
	TrabalhoEntity trabalho;
	
	private boolean verifica;
	
	private UploadedFile file;
	
	private String destino;
	
	private File dir;
	
	byte[] bytes = new byte[2*1024*1024];
		
	@PostConstruct
	public void init(){		
		user = (LoginEntity) SessionUtil.getParam("UserLoged");
		posInit();
		destino = "C:\\Develop\\Arguments\\workspace\\Arguments\\src\\main\\Trabalhos\\" + user.getId() + "\\";
	}
	
	private void posInit(){
		trabalhoDTO = new TrabalhoDTO();
		trabalho = new TrabalhoEntity();
		edit = false;
	}
	
	public String CriaTrabalho(){
		FacesContext context = FacesContext.getCurrentInstance();
		GregorianCalendar calendar = new GregorianCalendar();
		TrabalhoEntity trabalho = new TrabalhoEntity();
		DateFormat formatador = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss");
		String tempo = formatador.format(calendar.getTimeInMillis());
		int read = 0;
		
		if((!trabalhoDTO.getNomeTrabalho().isEmpty() && trabalhoDTO.getNomeTrabalho() != null)){
			if(valida(file)){
				if(!(verifica = (new File(destino + "\\").exists()))){
					dir = new File(destino + "\\");
					dir.mkdir();
				}
				
				try(InputStream is = file.getInputstream();
						OutputStream out = new FileOutputStream(destino + user.getId() + "_" + tempo + ".pdf")){
					while((read = is.read(bytes)) != -1){
						out.write(bytes, 0, read);
					}
					
					trabalhoDTO.setCaminho(destino);
					trabalhoDTO.setUsuario(user.getIdUsuario());
					trabalhoDTO.setNomearquivo(user.getId() + "_" + tempo + ".pdf");
					trabalho = trabalhoService.insert(trabalhoDTO);
					context.addMessage(null, new FacesMessage(ERRO_01, "Cadastrado com Sucesso"));
				}catch(Exception e){
					LOG.warning(ERRO_01 + " Erro ");
					context.addMessage(null, new FacesMessage(ERRO_01, "Erro cath"));
					e.printStackTrace();
					return "trabalho.xhtml?faces-redirect=true";
				}
			}
			trabalho = trabalhoService.insert(trabalhoDTO);
			
			posInit();
			carregaLista();
			context.addMessage(null, new FacesMessage(SUCESSO_01, "Cadastro com Sucesso"));
		}
		
		return "artigo.xhtml?faces-redirect=true";
	}
	
	public boolean valida(UploadedFile newFile){
		final int MAX_SIZE = 2 * 1024 * 1024;
		UploadedFile val = newFile;
		
		if(val.getSize() > MAX_SIZE){
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("ARQUIVO grande demais", "Tamanho maximo de 2MB"));
			return false;
		}else if(!val.getFileName().endsWith(".pdf")){
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage( "TIPO DO ARQUIVO INVALIDO", "O Arquivo deve ser .PDF"));
			return false;
		}
		
		return true;
	}
	
	public boolean getSize2() {

		if (file == null) {
			return true;
		} else {

			return false;

		}
	}
	
	private void carregaLista(){
		listaTrabalho = trabalhoService.findAllTrabalhos();
	}

	public List<TrabalhoEntity> getListaTrabalho() {
		return listaTrabalho;
	}

	public void setListaTrabalho(List<TrabalhoEntity> listaTrabalho) {
		this.listaTrabalho = listaTrabalho;
	}

	public TrabalhoDTO getTrabalhoDTO() {
		return trabalhoDTO;
	}

	public void setTrabalhoDTO(TrabalhoDTO trabalhoDTO) {
		this.trabalhoDTO = trabalhoDTO;
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

	public TrabalhoService getTrabalhoService() {
		return trabalhoService;
	}

	public void setTrabalhoService(TrabalhoService trabalhoService) {
		this.trabalhoService = trabalhoService;
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

}
