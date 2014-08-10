package gui;

import java.util.HashMap;

import javax.faces.bean.ManagedBean;

import util.EmailException;
import util.IdadeException;
import util.LoginException;
import util.NomeException;
import util.SenhaException;
import business.model.UsuarioAB;
import business.controllers.UsuarioControllerIF;
import business.controllers.UsuarioController;

@ManagedBean(name = "usuario")
public class UsuarioBean {
	private UsuarioAB usuario;
	private UsuarioAB[] pagina;
	private UsuarioControllerIF controller = new UsuarioController();
	
	public int getInit2(){
		HashMap<String, Object> user = new HashMap<String, Object>();
		user.put("nome", "igorrr");
		user.put("idade", 25);
		user.put("email", "igor@email.com");
		
		try {
			controller.create(user);
		} catch (EmailException | IdadeException | LoginException
				| NomeException | SenhaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 2;
	}
	
	public UsuarioAB[] getList(){
		this.pagina = controller.listAll();
		
		System.out.println(this.pagina.length);
		
		return this.pagina;
	}
	
	public UsuarioAB[] getList(Long max, Long offset){
		this.pagina = controller.listAll(offset, max);
		
		return this.pagina;
	}
}