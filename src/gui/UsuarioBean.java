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
	private UsuarioAB user;
	private UsuarioControllerIF controller = new UsuarioController();
	
	public Long create(String nome, String email, String telefone, String idade, String cpf, String sexo){
		HashMap<String, Object> newUser = new HashMap<String, Object>();
		newUser.put("nome", nome);
		newUser.put("email", email);
		newUser.put("telefone", telefone);
		newUser.put("idade", Long.parseLong(idade));
		newUser.put("cpf", cpf);
		newUser.put("sexo", sexo);
		
		Long id = null;
		try {
			id = controller.create(newUser);
		} catch (EmailException | IdadeException | LoginException
				| NomeException | SenhaException e) {
			e.printStackTrace();
		}
		
		return id;
	}
	
	public String show(Long id){
		this.user = controller.get(id);
		
		return null;
	}
	
	public UsuarioAB[] getList(){
		return controller.listAll();
	}
	
	public UsuarioAB[] getList(Long max, Long offset){
		return controller.listAll(offset, max);
	}
}