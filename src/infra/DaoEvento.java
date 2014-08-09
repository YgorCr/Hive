package infra;

import java.util.HashMap;

import business.model.Evento;
import business.model.EventoAB;

public class DaoEvento extends DaoMemo<EventoAB>{
	private HashMap<Long, EventoAB> DB;
	private static DaoEvento dao;
	private Long genID;
	
	private DaoEvento() {
		this.DB = new HashMap<Long, EventoAB>();
		genID = 0L;
	}
	
	public static DaoIF<EventoAB> getInstance(){
		if(DaoEvento.dao == null)
			DaoEvento.dao = new DaoEvento();
		return DaoEvento.dao;
	}
	
	@Override
	public EventoAB create(HashMap<String, Object> obj) {
		EventoAB evento = null;
		
		evento = new Evento();
		
		obj.put("id", genID++);
		
		this.setCampos(obj, evento);
		
		this.getDB().put(evento.getId(), evento);
		
		return evento;
	}

	@Override
	protected HashMap<Long, EventoAB> getDB() {
		return this.DB;
	}

}
