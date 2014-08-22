/*
 * Padrão: Singleton
 * Factory
 */

package infra;

import java.util.HashMap;

import business.model.Usuario;
import business.model.UsuarioAB;

public class DaoUsuarioMemo extends DaoMemo<UsuarioAB> {
	private Long genId;
	private HashMap<Long, UsuarioAB> DB;
	private static DaoUsuarioMemo dao;
	
	private DaoUsuarioMemo() {
		this.genId = 0L;
		this.DB = new HashMap<Long, UsuarioAB>();
	}
	
	public static DaoIF<UsuarioAB> getInstance() {
		if(DaoUsuarioMemo.dao == null)
			DaoUsuarioMemo.dao = new DaoUsuarioMemo();
		return DaoUsuarioMemo.dao;
	}
	
	@Override
	public UsuarioAB create(HashMap<String, Object> obj){
		Usuario newObj = new Usuario();
		obj.put("id", genId++);
		
		this.setCampos(obj, newObj);
		
		this.DB.put(newObj.getId(), newObj);
		return newObj;
	}
	
	@Override
	protected HashMap<Long, UsuarioAB> getDB() {
		return DB;
	}
}
