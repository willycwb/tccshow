package br.com.arguments.repository;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.arguments.entity.GruposEntity;

@Stateless
public class GruposDAO extends BaseDAO {
	
	public List<GruposEntity> findAllGrupos(){
	
		TypedQuery<GruposEntity> query = getManager()
				.createQuery("FROM GruposEntity", GruposEntity.class);
		
		List<GruposEntity> ls = query.getResultList();
		
		if(ls.isEmpty()){
			return null;
		}
		
		return ls;
	
	}
	
	public GruposEntity insert(GruposEntity grupo){
		return getManager().merge(grupo);
	}
	
	public void remove(GruposEntity grupo){
		Query query = getManager().createQuery("DELETE GruposEntity WHERE id = :id");
		query.setParameter("id", grupo.getId());
		query.executeUpdate();
	}

}
