package gui;

import javax.faces.bean.ManagedBean;

import business.model.UsuarioAB;
import business.controllers.UsuarioControllerIF;
import business.controllers.UsuarioController;

@ManagedBean(name = "usuario")
public class UsuarioBean {
	private UsuarioAB usuario;
	private UsuarioControllerIF controller = new UsuarioController();
	
	public UsuarioAB[] list(){
		return controller.listAll();
	}
	
	public UsuarioAB[] list(Long max, Long offset){
		return controller.listAll(offset, max);
	}
}