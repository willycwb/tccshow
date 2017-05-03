package br.com.arguments.repository;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.arguments.entity.CursosEntity;
import br.com.arguments.entity.TrabalhoEntity;

@Stateless
public class TrabalhoDAO extends BaseDAO{
	
	public List<TrabalhoEntity> findAllActive(){
		TypedQuery<TrabalhoEntity> query = getManager()
				.createQuery("FROM TrabalhoEntity L WHERE L.ativo = :ativo", TrabalhoEntity.class);
		query.setParameter("ativo", true);
		List<TrabalhoEntity> ls = query.getResultList();
		
		if(ls.isEmpty()){
			return null;
		}
		
		return ls;
	}
	
	public List<CursosEntity> findAllCursos(){
		TypedQuery<CursosEntity> query = getManager()
				.createQuery("FROM CursosEntity", CursosEntity.class);
		List<CursosEntity> ls = query.getResultList();
		
		if(ls.isEmpty()){
			return null;
		}
		
		return ls;
	}
	
	public TrabalhoEntity insert(TrabalhoEntity trabalho){
		return getManager().merge(trabalho);
	}
	
	public void update(TrabalhoEntity entity){
		Query query = getManager().createQuery("UPDATE TrabalhoEntity SET nome = :nome, descricao = :descricao, numCurso = :numCurso, caminho = :caminho, nomearq = :nomearq " + "WHERE id = :id");
		query.setParameter("id", entity.getId());
		query.setParameter("nome", entity.getNome());
		query.setParameter("descricao", entity.getDescricao());
		query.setParameter("nomearq", entity.getNomearq());
		query.setParameter("caminho", entity.getCaminho());
		query.setParameter("numCurso", entity.getNumCurso());
		query.executeUpdate();
	}
	
	public void remove(TrabalhoEntity entity){
		Query query = getManager().createQuery("DELETE TrabalhoEntity WHERE id = :id ");
		query.setParameter("id", entity.getId());
		query.executeUpdate();
	}
}
