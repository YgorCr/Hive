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
	private UsuarioAB user = new UsuarioAB() {};
	private UsuarioControllerIF controller = new UsuarioController();
	
	public Long create(){	
//		public Long create(String nome, String email, String telefone, String idade, String cpf, String sexo){
		HashMap<String, Object> newUser = new HashMap<String, Object>();
//		newUser.put("nome", nome);
//		newUser.put("email", email);
//		newUser.put("telefone", telefone);
//		newUser.put("idade", Long.parseLong(idade));
//		newUser.put("cpf", cpf);
//		newUser.put("sexo", sexo);
		System.out.println("GREATE ops create");
		System.out.println("email: " + this.user.getEmail());
		System.out.println("nome: "+ this.user.getNome());
		
		newUser.put("nome", user.getNome());
		newUser.put("email", user.getEmail());
		newUser.put("telefone", user.getTelefone());
		newUser.put("idade", user.getIdade());
		newUser.put("cpf", user.getCpf());
		newUser.put("sexo", user.getSexo());
		
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
	
	public void setNome(String nome){
		System.out.println("set Working: " + nome);
		this.user.setNome(nome);
	}
	
	public void setCpf(String cpf){
		this.user.setCpf(cpf);
	}
	
	public void setEmail(String email){
		System.out.println("set Working: " + email);
		this.user.setEmail(email);
	}
	
	public void setIdade(String idade){
		this.user.setIdade(Integer.parseInt(idade));
	}
	
	public void setSexo(String sexo){
		this.user.setSexo(sexo);
	}
	
	public void setTelefone(String telefone){
		this.user.setTelefone(telefone);
	}
	
	public String getCpf(){
		return this.user.getCpf();
	}
	
	public String getEmail(){
		System.out.println("get Working: " + this.user.getEmail());
		return this.user.getEmail();
	}
	
	public String getNome(){
		System.out.println("get Working: " + this.user.getNome());
		return this.user.getNome();
	}
	
	public String getSexo(){
		return this.user.getSexo();
	}
	
	public String getTelefone(){
		return this.user.getTelefone();
	}
	
	public String getIdade(){
		return this.user.getIdade().toString();
	}
	
	public String getId(){
		return this.user.getId().toString();
	}
}