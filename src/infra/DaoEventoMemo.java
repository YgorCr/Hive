package infra;

import java.util.HashMap;

import business.model.Evento;
import business.model.EventoAB;

public class DaoEventoMemo extends DaoMemo<EventoAB>{
	private HashMap<Long, EventoAB> DB;
	private static DaoEventoMemo dao;
	private Long genID;
	
	private DaoEventoMemo() {
		this.DB = new HashMap<Long, EventoAB>();
		genID = 0L;
	}
	
	public static DaoIF<EventoAB> getInstance(){
		if(DaoEventoMemo.dao == null)
			DaoEventoMemo.dao = new DaoEventoMemo();
		return DaoEventoMemo.dao;
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
