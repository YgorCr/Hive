/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package business.controllers;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import infra.DaoUsuario;
import util.EmailException;
import util.IdadeException;
import util.LoginException;
import util.NomeException;
import util.SenhaException;
import business.model.UsuarioAB;

/**
 *
 * @author ygor
 */
public class UsuarioController{
    
    public void create (String nome, String email, int idade, String cpf) throws EmailException, IdadeException, LoginException, NomeException, SenhaException{
        validaNome(nome);
        validaEmail(email);
        validaIdade(idade);
        
        HashMap<String, Object> newUser = new HashMap<String, Object>();
        newUser.put("nome", nome);
        newUser.put("email", email);
        newUser.put("idade", idade);
        newUser.put("cpf", cpf);
        DaoUsuario.getInstance().create(newUser);
    }
    
    public UsuarioAB get(Long id){
    	HashMap<String, Object> query = new HashMap<String, Object>();
    	query.put("id", id);
    	return DaoUsuario.getInstance().findOneBy(query);
    }
    
    public List<UsuarioAB> listAll(){
    	return DaoUsuario.getInstance().listAll();
    }
        
    public void validaNome(String nome) throws NomeException{
        int nomeLen = nome.length();
        
        if(nomeLen > 300){
            throw new NomeException("O nome não deve conter mais que 300 caracteres!");
        }
        else if(nomeLen < 5){
            throw new NomeException("O nome não deve conter menos que 5 caracteres!");
        }
        else if(nome.matches(".*\\d.*")){
            throw new NomeException("O nome não deve conter números!");
        }
    }

    public void validaEmail(String email) throws EmailException{
        if(!email.matches(".*@.*")){
            throw new EmailException("Email Invalido! O campo email deve conter no mínimo um @.");
        }
        else if(email.startsWith("@")){
            throw new EmailException("Email Invalido! O campo email não pode começar com um @.");
        }else if(email.endsWith("@")){
            throw new EmailException("Email Invalido! O campo email não pode terminar com um @.");
        }
    }

    public void validaIdade(int idade) throws IdadeException{
        if(idade < 0){
            throw new IdadeException("A idade não deve ser menor que 0!");
        }
        else if(idade > 150){
            throw new IdadeException("A idade não deve ser maior que 150!");
        }
    }
    
    public HashMap<String, Object> toHashMap(UsuarioAB obj) {
		HashMap<String, Object> objMap = new HashMap<>();
        
        for(Field f: UsuarioAB.class.getDeclaredFields()){
            String metodo = "get" + f.getName().substring(0, 1).toUpperCase() + f.getName().substring(1);
            try {
				objMap.put( f.getName(), UsuarioAB.class.getMethod(metodo, new Class[0]).invoke(obj, new Object[0]));
			} catch (IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | NoSuchMethodException
					| SecurityException e) {
				e.printStackTrace();
			}
        }
        
        return objMap;
	}
    
    public List<HashMap<String, Object>> listToHashMap(List<UsuarioAB> list){
    	List<HashMap<String, Object>> res = new ArrayList<HashMap<String,Object>>(list.size());
    	
    	for (UsuarioAB user : list) {
			res.add(this.toHashMap(user));
		}
    	
    	return res;
    }
}
