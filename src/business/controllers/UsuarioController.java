/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package business.controllers;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import infra.DaoAbstractFactory;
import util.EmailException;
import util.IdadeException;
import util.LoginException;
import util.NomeException;
import util.SenhaException;
import util.StructureException;
import business.model.UsuarioAB;

/**
 *
 * @author ygor
 */
public class UsuarioController implements UsuarioControllerIF{

	@Override
	public Long create(HashMap<String, Object> objeto) throws EmailException, IdadeException, LoginException, NomeException, SenhaException, StructureException{
		validaNome((String) objeto.get("nome"));
        validaEmail((String) objeto.get("email"));
        validaIdade((int) objeto.get("idade"));
        
        UsuarioAB newUser = null;
		try {
			newUser = (UsuarioAB) DaoAbstractFactory.getInstance(UsuarioAB.class).create(objeto);
		} catch (IOException | ClassNotFoundException e) {
			System.out.println("UserCtrl: "+ e);
			throw new StructureException(
					"Erro de estrutura de arquivos ao criar usuario");// TODO Auto-generated catch block
		}
        
		return newUser.getId();
	}

	@Override
	public void update(Long id, HashMap<String, Object> objeto) throws EmailException, IdadeException, LoginException, NomeException, SenhaException, StructureException{
		validaNome((String) objeto.get("nome"));
        validaEmail((String) objeto.get("email"));
        validaIdade((int) objeto.get("idade"));
		
		try {
			DaoAbstractFactory.getInstance(UsuarioAB.class).update(id, objeto);
		} catch (ClassNotFoundException | IOException e) {
			throw new StructureException(
					"Erro de estrutura de arquivos ao atualizar usuario");// TODO Auto-generated catch block
		}
	}
	
	@Override
	public UsuarioAB get(Long id) throws StructureException{
    	try {
			return (UsuarioAB) DaoAbstractFactory.getInstance(UsuarioAB.class).get(id);
		} catch (ClassNotFoundException | IOException e) {
			throw new StructureException(
					"Erro de estrutura de arquivos ao recuperar usuarios");// TODO Auto-generated catch block
		}
	}
	
	@Override
	public void delete(Long id) throws StructureException {
		try {
			DaoAbstractFactory.getInstance(UsuarioAB.class).delete(id);
		} catch (ClassNotFoundException | IOException e) {
			throw new StructureException(
					"Erro de estrutura de arquivos ao deletar usuario");// TODO Auto-generated catch block
		}
	}

	@Override
	public UsuarioAB[] listAll(Long offset, Long max) throws StructureException {
		List<?> all = null;
		try {
			all = DaoAbstractFactory.getInstance(UsuarioAB.class).listAll(max, offset);
		} catch (ClassNotFoundException | IOException e) {
			throw new StructureException(
					"Erro de estrutura de arquivos ao listar usuarios");// TODO Auto-generated catch block
		}
    	return (UsuarioAB[]) all.toArray(new UsuarioAB[all.size()]);
	}

	@Override
	public UsuarioAB[] listAll() throws StructureException {
		List<?> all = null;
		try {
			all = DaoAbstractFactory.getInstance(UsuarioAB.class).listAll();
		} catch (ClassNotFoundException | IOException e) {
			throw new StructureException(
					"Erro de estrutura de arquivos ao listar usuarios");// TODO Auto-generated catch block
		}
    	return (UsuarioAB[]) all.toArray(new UsuarioAB[all.size()]);
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
    
    public List<HashMap<String, Object>> listToHashMap(UsuarioAB[] list){
    	List<HashMap<String, Object>> res = new ArrayList<HashMap<String,Object>>(list.length);
    	
    	for (UsuarioAB user : list) {
			res.add(this.toHashMap(user));
		}
    	
    	return res;
    }
}
