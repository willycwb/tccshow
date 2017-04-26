package br.com.arguments.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.arguments.dto.DebateDTO;
import br.com.arguments.entity.CursosEntity;
import br.com.arguments.entity.DebateCursoEntity;
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
		debate.setIdUsuarioEntity(dto.getUsuario());
		debate.setData_abertura(StringToDate(dto.getDataCriacao()));
		debate.setData_fechamento(StringToDate(dto.getDataFechamento()));
		debate.setStatus(1);
		debate.setCurso(dto.getCurso());
		debate.setIdInstituicaoCursos(dto.getIdInstituicaoCursos());
		debate.setTema(dto.getTemaDebate());
		
		return debateDao.insert(debate);
	}
	
	private Date StringToDate(String data){
		SimpleDateFormat formatado = new SimpleDateFormat("dd/MM/yyyy");
		try {
			return formatado.parse(data);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void remove(DebateEntity debate){
		debateDao.remove(debate);
	}

	public List<DebateCursoEntity> findDebatesByCursos(DebateEntity item) {
		return debateDao.findDebatesByCursos(item);
	}
	
}
