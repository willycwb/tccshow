package br.com.arguments.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.arguments.dto.GruposDTO;
import br.com.arguments.entity.CursosEntity;
import br.com.arguments.entity.GruposEntity;
import br.com.arguments.entity.InstituicaoEntity;
import br.com.arguments.entity.LoginEntity;
import br.com.arguments.entity.UsuarioEntity;
import br.com.arguments.repository.GruposDAO;

@Stateless
public class GruposService {
	
	@EJB
	private GruposDAO grupoDAO;
	
	public List<GruposEntity> findAllGrupos(){
		return grupoDAO.findAllGrupos();
	}
	
	public GruposEntity insert(GruposDTO gruposDTO, LoginEntity usuario){
		GruposEntity grupos = new GruposEntity();
		grupos.setNome(gruposDTO.getNomeGrupo());
		grupos.setDataInicial(gruposDTO.getDataInicialStamp());
		grupos.setCurso(gruposDTO.getCurso());
		grupos.setTipoGrupo(gruposDTO.getTipoGrupo());
		grupos.setQtdMaximaMembros(gruposDTO.getQtdMaximaMembros());
		grupos.setUsuario(usuario.getIdUsuario());
		grupos.setDataCriacao(dataAtual());
		
//		grupos.setPrivacidade(gruposDTO.getPrivacidade());
//		grupos.setMembros(gruposDTO.getMembros());
//		grupos.setIdUsuarioEntity(gruposDTO.getUsuario());
		
		return grupoDAO.insert(grupos);
	}
	
	public Timestamp dataAtual(){
		try{
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy hh:mm");
			String dataAtual = format.format(new Date());
		    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		    Date parsedDate = dateFormat.parse(dataAtual.toString());
		    Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
		    return timestamp;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public void removeGrupos(GruposEntity grupos){
		grupoDAO.remove(grupos);
	}

	public List<CursosEntity> findAllCursos() {
		return grupoDAO.findAllCursos();
	}

	public List<InstituicaoEntity> findAllInstituicaoById(CursosEntity curso) {
		return grupoDAO.findAllInstituicaoById(curso);
	}

	public List<UsuarioEntity> findAllAlunosByInstituicao(Integer instituicaoSelecionado) {
		return grupoDAO.findAllAlunosByInstituicao(instituicaoSelecionado);
	}

}
