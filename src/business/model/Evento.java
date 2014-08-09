package business.model;

import java.util.LinkedList;

public class Evento extends EventoAB{
	public Evento(){
		this.setListaModeradores(new LinkedList<Long>());
		this.setListaParticipantes(new LinkedList<Long>());
	}
}
