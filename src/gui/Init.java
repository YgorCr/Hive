package gui;

import java.util.Calendar;
import java.util.HashMap;

import javax.faces.bean.ManagedBean;

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

@ManagedBean
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
	
	private void initIngressos(){
		HashMap<String, Object> obj = new HashMap<String, Object>();
		obj.put("idEvento", 1);
		obj.put("valor", 3.25);
		Calendar umDiaQualquer = Calendar.getInstance();
		umDiaQualquer.set(Calendar.YEAR, 2015);
		obj.put("dataDeValidade", umDiaQualquer);
		try {
			ingrController.create(obj);
			ingrController.create(obj);
			ingrController.create(obj);
			ingrController.create(obj);
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
