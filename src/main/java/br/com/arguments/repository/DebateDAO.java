package br.com.arguments.repository;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.arguments.entity.CursosEntity;
import br.com.arguments.entity.DebateCursoEntity;
import br.com.arguments.entity.DebateEntity;

@Stateless
public class DebateDAO extends BaseDAO {
	
	public List<DebateEntity> findAllDebates() {

		TypedQuery<DebateEntity> query = getManager()
				.createQuery("FROM DebateEntity", DebateEntity.class);
		List<DebateEntity> ls = query.getResultList();
		
		if(ls.isEmpty()){
			return null;
		}
		
		return ls;
		
	}
	
	public List<CursosEntity> findCursos(){
		TypedQuery<CursosEntity> query = getManager()
				.createQuery("SELECT nome FROM CursoEntity",CursosEntity.class);
		List<CursosEntity> ls = query.getResultList();
		
		if(ls.isEmpty()){
			return null;
		}
		
		return ls;
	}
	
	public DebateEntity insert(DebateEntity debate){
		return getManager().merge(debate);
	}
	
	public void remove(DebateEntity debate){
		Query query = getManager().createQuery("DELETE DebateEntity WHERE id = :id");
		query.setParameter("id", debate.getId());
		query.executeUpdate();
	}
	
	public void update(DebateEntity entity){
		Query query = getManager().createQuery("UPDATE DebateEntity SET nome = :nome, tema = :tema, curso = :curso, data_abertura = :data_abertura, data_fechamento = :data_fechamento" + "WHERE id = :id");
		query.setParameter("id", entity.getId());
		query.setParameter("nome", entity.getNome());
		query.setParameter("tema", entity.getTema());
//		query.setParameter("curso", entity.getCurso());		
//		query.setParameter("data_abertura", entity.getData_abertura());
//		query.setParameter("data_fechamento", entity.getData_fechamento());
		query.executeUpdate();
	}

	public List<DebateCursoEntity> findDebatesByCursos(DebateEntity idDebateEntity) {
		
		TypedQuery<DebateCursoEntity> query = getManager()
				.createQuery("SELECT L FROM DebateCursoEntity L JOIN L.idDebateEntity "
						+ "WHERE L.idDebateEntity = :idDebateEntity ", DebateCursoEntity.class);
		query.setParameter("idDebateEntity", idDebateEntity);
		
		List<DebateCursoEntity> itens = query.getResultList();

		return itens;
		
		
	}

	public DebateCursoEntity insertComentarioDebate(DebateCursoEntity entity) {
		return getManager().merge(entity);
		
	}

	public List<DebateCursoEntity> findAllDebatesByDebate(DebateEntity debate) {

		TypedQuery<DebateCursoEntity> query = getManager()
				.createQuery("SELECT L FROM DebateCursoEntity L JOIN L.idDebateEntity "
						+ "WHERE L.idDebateEntity = :idDebateEntity ", DebateCursoEntity.class);
		query.setParameter("idDebateEntity", debate);
		
		List<DebateCursoEntity> itens = query.getResultList();
		
		return itens;
	}

	
}
