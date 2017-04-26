package br.com.arguments.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.arguments.repository.EventoDAO;
import br.com.arguments.dto.EventoDTO;
import br.com.arguments.entity.CursosEntity;
import br.com.arguments.entity.EventoEntity;
//import br.com.unieventos.entity.UsuarioEntity;
//import br.com.unieventos.entity.UsuarioEventoEntity;

@Stateless
public class EventoService {
	
	@EJB
	private EventoDAO eventoDAO;

	public List<EventoEntity> findAllActive(){
		return eventoDAO.findAllActive();
	}
//	
//	public List<EventoEntity> findAtivitByUser(UsuarioEntity user){
//		return eventoDAO.findEventoByUser(user);
//	}
//	
//	public EventoEntity findEventoById(Long user){
//		return eventoDAO.findEventoById(user);
//	}
//	
	public EventoEntity insert(EventoDTO dto){
		EventoEntity evento = new EventoEntity();
		evento.setNome(dto.getNome());
		evento.setDescricao(dto.getDescricao());
		evento.setNumCurso(dto.getCurso());
//		evento.setDataInicio(convertStringToDate(dto.getDataInicio()));
		evento.setDataCriacao(new Date());
		evento.setAtivo(true);
//		if (dto.isImagem()) { 
//			evento.setCaminho(dto.getCaminho());
//		} else {
//			evento.setImagem(false);
//			evento.setCaminho(null);
//		}
		return eventoDAO.insert(evento);
	}
//	
//	private Date convertStringToDate(String data){
//		SimpleDateFormat formatado = new SimpleDateFormat("dd/MM/yyyy");
//		try {
//			return formatado.parse(data);
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
//	
//	public void remove(EventoEntity entity){
//		eventoDAO.remove(entity);
//	}
//	
//	public boolean verifyParticipacao(EventoEntity evento, UsuarioEntity user){
//		return eventoDAO.verifyParticipacao(evento, user);
//	}
//	
//	public void participarEvento(UsuarioEventoEntity entity){
//		eventoDAO.participarEvento(entity);
//	}
//	
//	public void cancelParticipacaoEvent(EventoEntity evento, UsuarioEntity user){
//		eventoDAO.cancelParticipacaoEvent(evento,user);
//	}
//	
//	public void update(EventoDTO dto){
//		EventoEntity evento = new EventoEntity();
//		evento.setId(dto.getId().intValue());
//		evento.setNome(dto.getNome());
//		evento.setDescricao(dto.getDescricao());
//		evento.setDataInicio(convertStringToDate(dto.getDataInicio()));
//		evento.setDataFinal(convertStringToDate(dto.getDataFinal()));
//		evento.setDataCriacao(new Date());
//		evento.setAtivo(true);
//		eventoDAO.update(evento);
//	}
//	
//	public List<EventoEntity> pesquisaEvento(String nome){
//		return eventoDAO.pesquisaEvento(nome);
//	}
//	
//	public List<EventoEntity> findEventoByUser(UsuarioEntity user){
//		return eventoDAO.findEventoByUser(user);
//	}
//
//	public UsuarioEventoEntity findUsuarioEvento(UsuarioEntity user, EventoEntity evento) {
//		return eventoDAO.findUsuarioEvento(user,evento);
//	}
	public List<CursosEntity> findAllCursos() {
		return eventoDAO.findAllCursos();
	}
	
	
}
