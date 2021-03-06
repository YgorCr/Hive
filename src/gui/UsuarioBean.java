package gui;

import java.util.HashMap;

//import javax.faces.bean.ManagedBean;


import util.EmailException;
import util.IdadeException;
import util.LoginException;
import util.NomeException;
import util.SenhaException;
import util.StructureException;
import business.model.UsuarioAB;
import business.controllers.UsuarioControllerIF;
import business.controllers.UsuarioController;

//@ManagedBean(name = "usuario")
public class UsuarioBean {
	private UsuarioAB user = new UsuarioAB() {};
	private UsuarioControllerIF controller = new UsuarioController();
	
	public String create() throws StructureException{
		HashMap<String, Object> newUser = new HashMap<String, Object>();
		
		newUser.put("nome", this.user.getNome());
		newUser.put("email", this.user.getEmail());
		newUser.put("telefone", this.user.getTelefone());
		newUser.put("idade", this.user.getIdade());
		newUser.put("cpf", this.user.getCpf());
		newUser.put("sexo", this.user.getSexo());
		
		Long id = null;
		try {
			id = this.controller.create(newUser);
		} catch (EmailException | IdadeException | LoginException
				| NomeException | SenhaException e) {
			e.printStackTrace();
		}
		
		return "show?faces-redirect=true&id=" + id;
	}
	
	public String show(Long id) throws StructureException{
		this.user = this.controller.get(id);
		
		return null;
	}
	
	public String update(Long id) throws StructureException{
		HashMap<String, Object> oldUser = new HashMap<String, Object>();
		
		oldUser.put("nome", this.user.getNome());
		oldUser.put("email", this.user.getEmail());
		oldUser.put("telefone", this.user.getTelefone());
		oldUser.put("idade", this.user.getIdade());
		oldUser.put("cpf", this.user.getCpf());
		oldUser.put("sexo", this.user.getSexo());
				
		try {
			this.controller.update(id, oldUser);
		} catch (EmailException | IdadeException | LoginException
				| NomeException | SenhaException e) {
			e.printStackTrace();
		}
		
		return "show?faces-redirect=true&id=" + id;
	}
	
	public String delete(Long id) throws StructureException{
		this.controller.delete(id);
		
		return "/index?faces-redirect=true";
	}
	
	public UsuarioAB[] getList() throws StructureException{
		return this.controller.listAll();
	}
	
	public UsuarioAB[] getList(Long max, Long offset) throws StructureException{
		return this.controller.listAll(offset, max);
	}
	
	public void setId(String id){
		this.user.setId(Long.parseLong(id));
	}
	
	public void setNome(String nome){
		this.user.setNome(nome);
	}
	
	public void setCpf(String cpf){
		this.user.setCpf(cpf);
	}
	
	public void setEmail(String email){
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
		return this.user.getEmail();
	}
	
	public String getNome(){
		return this.user.getNome();
	}
	
	public String getSexo(){
		return this.user.getSexo();
	}
	
	public String getTelefone(){
		return this.user.getTelefone();
	}
	
	public String getIdade(){
		return (this.user.getIdade() == null)?(""):(this.user.getIdade().toString());
	}
	
	public String getId(){
		return (this.user.getId() == null)?(""):(this.user.getId().toString());
	}
}