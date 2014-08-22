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
		EventoAB evento = new Evento();
		obj.put("id", genID++);
		this.setCampos(obj, evento);		
		this.getDB().put(evento.getId(), evento);
		
		fileOut = new FileOutputStream("evento.dat", false); 
		writer = new ObjectOutputStream(fileOut);
		writer.writeObject(this.DB);
		fileOut.close();
		writer.close();

		return evento;
	}

	@Override
	protected HashMap<Long, EventoAB> getDB() throws IOException, ClassNotFoundException {
		fileIn = null;
		try{
	        fileIn = new FileInputStream("evento.dat");
	        reader = new ObjectInputStream(fileIn);
	        DB = (HashMap<Long, EventoAB>) reader.readObject();		        	       
		} catch (FileNotFoundException e) {
			System.out.println("Arquivo evento.dat ainda nao existe.");
		} 
		
		return DB;
	}

	@Override
	protected void saveDataState() throws IOException, ClassNotFoundException {
		FileOutputStream fileOut = new FileOutputStream("user.dat", false); // overwrite
		ObjectOutputStream writer = new ObjectOutputStream(fileOut);
		writer.writeObject(DB);
		fileOut.close();
		
	}

}
