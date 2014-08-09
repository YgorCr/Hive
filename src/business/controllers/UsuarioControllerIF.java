package business.controllers;

import java.util.HashMap;
import business.model.UsuarioAB;

public interface UsuarioControllerIF {
	public Long create(HashMap<String, Object> objeto);
	public void update(Long id, HashMap<String, Object> objeto);
	public UsuarioAB get(Long id);
	public void delete(Long id);
	public UsuarioAB[] listAll(Long offset, Long max);
	public UsuarioAB[] listAll();
}
