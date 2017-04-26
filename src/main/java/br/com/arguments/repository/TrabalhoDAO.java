package br.com.arguments.repository;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.arguments.entity.TrabalhoEntity;

@Stateless
public class TrabalhoDAO extends BaseDAO{
	
	public List<TrabalhoEntity> findAllTrabalhos(){
		
		TypedQuery<TrabalhoEntity>query = getManager().createQuery("FROM TrabalhoEntity", TrabalhoEntity.class);
		
		List<TrabalhoEntity> ls = query.getResultList();
		
		if(ls.isEmpty()){
			return null;
		}
		
		return ls;
	}
	
	public TrabalhoEntity insert(TrabalhoEntity trabalho){
		return getManager().merge(trabalho);
	}
	
	public void removeTrabalho(TrabalhoEntity trabalho){
		Query query = getManager().createQuery("DELETE TrabalhoEntity WHERE id = :id");
		query.setParameter("id", trabalho.getId());
		query.executeUpdate();
		
	}

}
