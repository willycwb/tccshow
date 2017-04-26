package br.com.arguments.repository;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import br.com.arguments.entity.LoginEntity;

@Stateless
public class LoginDAO extends BaseDAO {
	
	public LoginEntity save(LoginEntity login){
		return getManager().merge(login);
	}
//	
//	public List<LoginEntity> findAll() {
//		return getManager().createQuery("SELECT A FROM LOGIN A", LoginEntity.class).getResultList();
//	}
//
//	public List<LoginEntity> findByUsuario(String usuario) {
//		TypedQuery<LoginEntity> query = getManager().createQuery("SELECT U FROM LOGIN U WHERE U.NOME LIKE :NOME", LoginEntity.class);
//		query.setParameter("NOME", "%" + usuario + "%");
//		return query.getResultList();
//	}
	
	public LoginEntity findLogin(String user){
		TypedQuery<LoginEntity> query = getManager()
				.createQuery("FROM LoginEntity L WHERE L.usuario = :usuario", LoginEntity.class);
		query.setParameter("usuario", user);
		List<LoginEntity> ls = query.getResultList();
		
		if(ls.isEmpty()){
			return null;
		}
		
		return ls.get(0);
	}
	
}
