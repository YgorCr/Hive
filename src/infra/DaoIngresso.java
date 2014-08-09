package infra;

import java.util.HashMap;

import business.controllers.CodigoAdapter;
import business.controllers.CodigoIF;
import business.model.Ingresso;
import business.model.IngressoAB;
	
public class DaoIngresso extends DaoMemo<IngressoAB>{
	private HashMap<Long, IngressoAB> DB;
	private static DaoIngresso dao;

	private DaoIngresso() {
		this.DB = new HashMap<Long, IngressoAB>();
	}
	
	public static DaoIF<IngressoAB> getInstance(){
		if(DaoIngresso.dao == null)
			DaoIngresso.dao = new DaoIngresso();
		return DaoIngresso.dao;
	}
	
	@Override
	public IngressoAB create(HashMap<String, Object> obj) {
		// TODO Auto-generated method stub
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
		ingresso = new Ingresso();
		this.setCampos(obj, ingresso);
		this.getDB().put(ingresso.getId(), ingresso);
		
		return ingresso;
	}

	@Override
	protected HashMap<Long, IngressoAB> getDB() {
		// TODO Auto-generated method stub
		return this.DB;
	}
	
}
