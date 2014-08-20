package gui;

import java.util.Calendar;
import java.util.HashMap;

//import javax.faces.bean.ManagedBean;

import business.controllers.IngressoController;
import business.controllers.IngressoControllerIF;
import business.controllers.UsuarioController;
import business.controllers.UsuarioControllerIF;
import util.DataDeValidadeException;
import util.EmailException;
import util.IdadeException;
import util.LoginException;
import util.NomeException;
import util.PrecoException;
import util.SenhaException;

//@ManagedBean
public class Init {
	private static UsuarioControllerIF userController = new UsuarioController();
	private static Boolean rodou = false;
	private static IngressoControllerIF ingrController = new IngressoController();

	public String getTesteInit(){
		if(!rodou){
			initUsuarios();
			initIngressos();
			rodou = true;
		}
		
		return null;
	}
	
	private void initUsuarios(){
		HashMap<String, Object> user = new HashMap<String, Object>();

		try {
			user.put("nome", "usuarioA");
			user.put("idade", 18);
			user.put("email", "usuarioA@email.com");
			userController.create(user);
			
			user.put("nome", "usuarioB");
			user.put("idade", 19);
			user.put("email", "usuarioB@email.com");
			userController.create(user);
			
			user.put("nome", "usuarioC");
			user.put("idade", 20);
			user.put("email", "usuarioC@email.com");
			userController.create(user);
			
			user.put("nome", "usuarioD");
			user.put("idade", 21);
			user.put("email", "usuarioD@email.com");
			userController.create(user);
			
			user.put("nome", "usuarioE");
			user.put("idade", 22);
			user.put("email", "usuarioE@email.com");
			userController.create(user);
			
		} catch (EmailException | IdadeException | LoginException
				| NomeException | SenhaException e) {
			e.printStackTrace();
		}
	}
	
	private void initIngressos(){
		HashMap<String, Object> obj = new HashMap<String, Object>();
		obj.put("idEvento", 1L);
		obj.put("valor", (Double) 3.25);
		Calendar umDiaQualquer = Calendar.getInstance();
		umDiaQualquer.set(Calendar.YEAR, 2015);
		obj.put("dataDeValidade", umDiaQualquer);
		try {
			ingrController.create(obj);
			//ingrController.create(obj);
			//ingrController.create(obj);
			//ingrController.create(obj);
		} catch (PrecoException e) {
			// TODO Auto-generated catch block
			System.out.println("NAOAAAAAAA");
			e.printStackTrace();
		} catch (DataDeValidadeException e) {
			// TODO Auto-generated catch block
			System.out.println("NAOAAAAAAA");
			e.printStackTrace();
		}
	}
}
