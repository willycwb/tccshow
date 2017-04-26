package br.com.arguments.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.arguments.dto.GruposDTO;
import br.com.arguments.entity.GruposEntity;
import br.com.arguments.repository.GruposDAO;

@Stateless
public class GruposService {
	
	@EJB
	private GruposDAO grupoDAO;
	
	public List<GruposEntity> findAllGrupos(){
		return grupoDAO.findAllGrupos();
	}
	
	public GruposEntity insert(GruposDTO gruposDTO){
		GruposEntity grupos = new GruposEntity();
		grupos.setNome(gruposDTO.getNomeGrupo());
		grupos.setPrivacidade(gruposDTO.getPrivacidade());
		grupos.setMembros(gruposDTO.getMembros());
		grupos.setCurso(grupos.getCurso());
		grupos.setIdUsuarioEntity(gruposDTO.getUsuario());
		
		return grupoDAO.insert(grupos);
	}
	
	public void removeGrupos(GruposEntity grupos){
		grupoDAO.remove(grupos);
	}

}
