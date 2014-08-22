package infra;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import business.controllers.CodigoAdapter;
import business.controllers.CodigoIF;
import business.model.Ingresso;
import business.model.IngressoAB;
import business.model.UsuarioAB;
	
public class DaoIngresso extends DaoFile<IngressoAB>{
	private HashMap<Long, IngressoAB> DB;
	private static DaoIngresso dao;
	private Long genID;
	
	private FileOutputStream fileOut;
    private ObjectOutputStream writer;
    
    private FileInputStream fileIn;
    private ObjectInputStream reader;
	
	private DaoIngresso() {
		this.DB = new HashMap<Long, IngressoAB>();
		genID = 0L;
	}
	
	public static DaoIF<IngressoAB> getInstance(){
		if(DaoIngresso.dao == null)
			DaoIngresso.dao = new DaoIngresso();
		return DaoIngresso.dao;
	}
	
	@Override
	public IngressoAB create(HashMap<String, Object> obj) throws IOException, ClassNotFoundException {
		CodigoIF geradorCodigo = new CodigoAdapter();
		
		String codigo = null;
		HashMap<String,Object> query = new HashMap<String, Object>();
		IngressoAB ingresso = null;
		//garante codigo unico
		do{
			codigo = geradorCodigo.geraCodigo();
			query.put("codigo", codigo);
		    ingresso = this.findOneBy(query);
		}while(ingresso != null);
		
		obj.put("codigo", geradorCodigo.geraCodigo());
		obj.put("id", this.genID++);
		ingresso = new Ingresso();
		this.setCampos(obj, ingresso);
		this.getDB().put(ingresso.getId(), ingresso);
		
		fileOut = new FileOutputStream("ingresso.dat", false); 
		writer = new ObjectOutputStream(fileOut);
		writer.writeObject(DB);
		fileOut.close();
		
		return ingresso;
	}

	@Override
	protected HashMap<Long, IngressoAB> getDB() throws IOException, ClassNotFoundException {
		fileIn = null;
try{
        fileIn = new FileInputStream("ingresso.dat");
        reader = new ObjectInputStream(fileIn);
        DB = (HashMap<Long, IngressoAB>) reader.readObject();
	} catch (FileNotFoundException e) {
		System.out.println("Arquivo ingresso.dat ainda nao existe.");
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
