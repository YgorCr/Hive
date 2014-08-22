/*
 * Padrão: Singleton
 * Factory
 */

package infra;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.HashMap;

import business.model.Usuario;
import business.model.UsuarioAB;

public class DaoUsuario extends DaoFile<UsuarioAB> {
	private Long genId;
	private HashMap<Long, UsuarioAB> DB;
	private static DaoUsuario dao;
	
	private FileOutputStream fileOut;
    private ObjectOutputStream writer;
    
    private FileInputStream fileIn;
    private ObjectInputStream reader;
	
	private DaoUsuario() {
		this.genId = 0L;
		this.DB = new HashMap<Long, UsuarioAB>();
				
	}
	
	public static DaoIF<UsuarioAB> getInstance() {
		if(DaoUsuario.dao == null)
			DaoUsuario.dao = new DaoUsuario();
		return DaoUsuario.dao;
	}
	
	@Override
	public UsuarioAB create(HashMap<String, Object> obj) throws IOException, ClassNotFoundException{
		Usuario newObj = new Usuario();
		obj.put("id", genId++);
		this.setCampos(obj, newObj);		
		this.DB.put(newObj.getId(), newObj);

		fileOut = new FileOutputStream("user.dat", false); 
		writer = new ObjectOutputStream(fileOut);
		writer.writeObject(this.DB);
		fileOut.close();
		writer.close();
		
		return newObj;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected HashMap<Long, UsuarioAB> getDB() throws IOException, ClassNotFoundException {
		fileIn = null;
 
		try {
	        fileIn = new FileInputStream("user.dat");
	        reader = new ObjectInputStream(fileIn);
	        DB = (HashMap<Long, UsuarioAB>) reader.readObject();		        	       
		} catch (FileNotFoundException e) {
			System.out.println("Arquivo user.dat ainda nao existe.");
		} 
		
		return DB;
	}
	
	protected void saveDataState() throws IOException {
		FileOutputStream fileOut = new FileOutputStream("user.dat", false); // overwrite
		ObjectOutputStream writer = new ObjectOutputStream(fileOut);
		writer.writeObject(DB);
		fileOut.close();
	
	}
}
