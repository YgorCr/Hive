package infra;

import java.util.HashMap;

import business.controllers.CodigoAdapter;
import business.controllers.CodigoIF;
import business.model.Ingresso;
import business.model.IngressoAB;
	
public class DaoIngressoMemo extends DaoMemo<IngressoAB>{
	private HashMap<Long, IngressoAB> DB;
	private static DaoIngressoMemo dao;
	private Long genID;
	
	private DaoIngressoMemo() {
		this.DB = new HashMap<Long, IngressoAB>();
		genID = 0L;
	}
	
	public static DaoIF<IngressoAB> getInstance(){
		if(DaoIngressoMemo.dao == null)
			DaoIngressoMemo.dao = new DaoIngressoMemo();
		return DaoIngressoMemo.dao;
	}
	
	@Override
	public IngressoAB create(HashMap<String, Object> obj) {
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
		
		return ingresso;
	}

	@Override
	protected HashMap<Long, IngressoAB> getDB() {
		return this.DB;
	}
	
}
