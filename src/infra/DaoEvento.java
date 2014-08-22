package infra;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import business.model.Evento;
import business.model.EventoAB;
import business.model.UsuarioAB;

public class DaoEvento extends DaoFile<EventoAB>{
	private HashMap<Long, EventoAB> DB;
	private static DaoEvento dao;
	private Long genID;	
	
	private FileOutputStream fileOut;
    private ObjectOutputStream writer;
    
    private FileInputStream fileIn;
    private ObjectInputStream reader;
	
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
	public EventoAB create(HashMap<String, Object> obj) throws IOException, ClassNotFoundException {
		EventoAB evento = null;
		
		evento = new Evento();
		
		obj.put("id", genID++);
		
		this.setCampos(obj, evento);
		
		this.getDB().put(evento.getId(), evento);
		
		fileOut = new FileOutputStream("c:\\event.dat", false); 
		writer = new ObjectOutputStream(fileOut);
		writer.writeObject(DB);
		fileOut.close();
		
	
		
		return evento;
	}

	@Override
	protected HashMap<Long, EventoAB> getDB() throws IOException, ClassNotFoundException {
		fileIn = null;

        fileIn = new FileInputStream("C:\\event.dat");
        reader = new ObjectInputStream(fileIn);
        DB = (HashMap<Long, EventoAB>) reader.readObject();		        	       

		
		return DB;
	}

}
