package br.com.arguments.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.arguments.entity.DebateEntity;
import br.com.arguments.entity.EventoEntity;
import br.com.arguments.entity.TimeLineEntity;
import br.com.arguments.entity.TipoConteudoDebateEntity;
import br.com.arguments.entity.TipoConteudoEventoEntity;
import br.com.arguments.repository.TimeLineDAO;

@Stateless
public class TimeLineService {

	@EJB
	private TimeLineDAO timeLineDAO;
	
	public TimeLineEntity insertEvent(EventoEntity event) {
		
		TipoConteudoEventoEntity tpe = new TipoConteudoEventoEntity();
		tpe.setNome(event.getNome());
		tpe.setDescricao(event.getDescricao());
		tpe.setDataInicio(event.getDataInicio());
		
		TipoConteudoEventoEntity tpEntity = timeLineDAO.insertTipoConteudoEvento(tpe);
		
		TimeLineEntity tl = new TimeLineEntity();
		tl.setIdCurso(event.getNumCurso().getId().intValue());
		tl.setIdTipoConteudoEvento(tpEntity);
		tl.setDataCriacao(dataAtual());
		tl.setTipoConteudo(1);
		
		TimeLineEntity tlEntity = timeLineDAO.insert(tl);
		
		return tlEntity;
	}

	public List<TimeLineEntity> findTimeLineByCurso(int id) {
		return timeLineDAO.findTimeLineByCurso(id);
		
	}

	public List<TipoConteudoEventoEntity> findTipoConteudoEvento(List<Integer> listaIdConteudoEvento) {
		return timeLineDAO.findTipoConteudoEvento(listaIdConteudoEvento);
	}

	public TimeLineEntity insertDebate(DebateEntity debate) {
		TipoConteudoDebateEntity tpd = new TipoConteudoDebateEntity();
		tpd.setNome(debate.getNome());
		tpd.setTema(debate.getTema());
		
		TipoConteudoDebateEntity tpEntity = timeLineDAO.insertTipoConteudoDebate(tpd);
		
		TimeLineEntity tl = new TimeLineEntity();
		//tl.setIdCurso(debate.getCurso().getId().intValue());//show vai mudar
		tl.setIdCurso(1);
		tl.setIdTipoConteudoDebate(tpEntity);
		tl.setDataCriacao(dataAtual());
		tl.setTipoConteudo(2);
		
		TimeLineEntity tlEntity = timeLineDAO.insert(tl);
		
		return tlEntity;
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
	
}
