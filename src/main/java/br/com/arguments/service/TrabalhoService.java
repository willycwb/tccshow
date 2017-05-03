package br.com.arguments.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.arguments.dto.TrabalhoDTO;
import br.com.arguments.entity.CursosEntity;
import br.com.arguments.entity.TrabalhoEntity;
import br.com.arguments.repository.TrabalhoDAO;

@Stateless
public class TrabalhoService {
	
	@EJB
	private TrabalhoDAO trabalhoDAO;
	
	public List<TrabalhoEntity> findAllActive(){
		return trabalhoDAO.findAllActive();
	}
	
	public List<CursosEntity> findAllCursos(){
		return trabalhoDAO.findAllCursos();
	}
	
	public TrabalhoEntity insert(TrabalhoDTO dto){
		TrabalhoEntity trabalho = new TrabalhoEntity();
		trabalho.setNome(dto.getNome());
		trabalho.setDescricao(dto.getDescricao());
		trabalho.setCaminho(dto.getCaminho());
		trabalho.setNomearq(dto.getNomearq());
		trabalho.setNumCurso(dto.getCurso());
		trabalho.setAtivo(true);
		return trabalhoDAO.insert(trabalho);
	}
	
	public void remove(TrabalhoEntity entity){
		trabalhoDAO.remove(entity);
	}
	
	public void update(TrabalhoDTO dto){
		TrabalhoEntity trabalho = new TrabalhoEntity();
		trabalho.setId(dto.getId());
		trabalho.setNome(dto.getNome());
		trabalho.setDescricao(dto.getDescricao());
		trabalho.setCaminho(dto.getCaminho());
		trabalho.setNomearq(dto.getNomearq());
		trabalho.setNumCurso(dto.getCurso());
		trabalho.setAtivo(true);
		trabalhoDAO.update(trabalho);
	}
	

}
