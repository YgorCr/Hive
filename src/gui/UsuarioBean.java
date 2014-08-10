package gui;

import javax.faces.bean.ManagedBean;

import business.model.UsuarioAB;
import business.controllers.UsuarioControllerIF;
import business.controllers.UsuarioController;

@ManagedBean(name = "usuario")
public class UsuarioBean {
	private UsuarioControllerIF controller = new UsuarioController();
		
	public UsuarioAB[] getList(){
		return controller.listAll();
	}
	
	public UsuarioAB[] getList(Long max, Long offset){
		return controller.listAll(offset, max);
	}
}