package br.com.arguments.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.arguments.dto.DebateDTO;
import br.com.arguments.entity.CursosEntity;
import br.com.arguments.entity.DebateEntity;
import br.com.arguments.repository.DebateDAO;

@Stateless
public class DebateService {

	@EJB
	private DebateDAO debateDao;

	public List<DebateEntity> findAllDebates() {
		return debateDao.findAllDebates();
	}
	
	public List<CursosEntity> findCursos(){
		return debateDao.findCursos();
	}
	
	public DebateEntity insert(DebateDTO dto){
		DebateEntity debate = new DebateEntity();
		debate.setNome(dto.getNomeDebate());
		debate.setDataAbertura(dto.getDataCriacaoStamp());
		debate.setDataFechamento(dto.getDataFechamentoStamp());
		debate.setIdCurso(dto.getIdCursos());
		debate.setIdUsuarioEntity(dto.getUsuario());
		debate.setStatus(1);
		debate.setTema(dto.getTemaDebate());
		return debateDao.insert(debate);
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
	
//	public void remove(DebateEntity debate){
//		debateDao.remove(debate);
//	}
//
//	public List<DebateCursoEntity> findDebatesByCursos(DebateEntity item) {
//		return debateDao.findDebatesByCursos(item);
//	}
	
}
