package gui;

import java.util.HashMap;

import javax.faces.bean.ManagedBean;

import business.controllers.UsuarioController;
import business.controllers.UsuarioControllerIF;
import util.EmailException;
import util.IdadeException;
import util.LoginException;
import util.NomeException;
import util.SenhaException;

@ManagedBean
public class Init {
	private static UsuarioControllerIF userController = new UsuarioController();
	private static Boolean rodou = false;
	
	public String getTesteInit(){
		if(!rodou){
			initUsuarios();
			
			rodou = true;
		}
		
		return null;
	}
	
	private void initUsuarios(){
		HashMap<String, Object> user = new HashMap<String, Object>();
		user.put("nome", "igorrr");
		user.put("idade", 25);
		user.put("email", "igor@email.com");
		
		try {
			userController.create(user);
		} catch (EmailException | IdadeException | LoginException
				| NomeException | SenhaException e) {
			e.printStackTrace();
		}
	}
}
