package br.com.arguments.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.arguments.dto.TrabalhoDTO;
import br.com.arguments.entity.TrabalhoEntity;
import br.com.arguments.repository.TrabalhoDAO;

@Stateless
public class TrabalhoService {
	
	@EJB
	private TrabalhoDAO trabalhoDAO;
	
	public List<TrabalhoEntity>findAllTrabalhos(){
		return trabalhoDAO.findAllTrabalhos();
	}
	
	public TrabalhoEntity insert(TrabalhoDTO trabalhoDTO){
		TrabalhoEntity trabalhoEntity = new TrabalhoEntity();
		trabalhoEntity.setNome(trabalhoDTO.getNomeTrabalho());
		trabalhoEntity.setCurso(trabalhoDTO.getCurso());
		trabalhoEntity.setComentario(trabalhoDTO.getComentario());
		
		return trabalhoDAO.insert(trabalhoEntity);
		
	}
	
	public void removeTrabalho(TrabalhoEntity trabalhoEntity){
		trabalhoDAO.removeTrabalho(trabalhoEntity);
	}
	

}
