package gui;

import javax.faces.bean.ManagedBean;

import business.model.UsuarioAB;
import business.controllers.UsuarioControllerIF;
import business.controllers.UsuarioController;

@ManagedBean(name = "usuario")
public class UsuarioBean {
	private UsuarioAB usuario;
	private UsuarioAB[] pagina;
	private UsuarioControllerIF controller = new UsuarioController();
	
	public String getId(){
		return usuario.getId().toString();
	}
	
	public String getNome(){
		return usuario.getNome();
	}

	public String getEmail(){
		return usuario.getEmail();
	}
	
	public String getCpf(){
		return usuario.getCpf();
	}
	
	public String getSexo(){
		return usuario.getSexo();
	}
	
	public String getTelefone(){
		return usuario.getTelefone();
	}

	public String getIdade(){
		return usuario.getIdade().toString();
	}
		
	public UsuarioAB[] getList(){
		this.pagina = controller.listAll();
		
		return this.pagina;
	}
	
	public UsuarioAB[] getList(Long max, Long offset){
		this.pagina = controller.listAll(offset, max);
		
		return this.pagina;
	}
}