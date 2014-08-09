/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package business.controllers;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import util.DataDeValidadeException;
import util.EmailException;
import util.IdadeException;
import util.LoginException;
import util.NomeException;
import util.PrecoException;
import util.SenhaException;

/**
 *
 * @author ygor
 */
public class BusinessFacades {
    private static BusinessFacades business;
    
    //controllers
    IngressoController ingressoC;
    
    private BusinessFacades() {
    	ingressoC = new IngressoController();
    }
   
    public static BusinessFacades getInstance(){
        if(business == null){
            business = new BusinessFacades();
        }
        return business;
    }
    
    public void usuarioCreate(String nome, String email, int idade, String cpf) throws EmailException, IdadeException, LoginException, NomeException, SenhaException{
        UsuarioController userManager = new UsuarioController();
        userManager.create(nome, email, idade, cpf);
    }
    
    public HashMap<String, Object> usuarioGet(Long id){
        UsuarioController userManager = new UsuarioController();
        return userManager.toHashMap(userManager.get(id));
    }
    
    public List<HashMap<String, Object>> usuarioListAll(){
    	UsuarioController userManager = new UsuarioController();
    	return userManager.listToHashMap(userManager.listAll());
    }
    
    public String ingressoCreate(Long idEvento, Long idUsuario, Double valor, Calendar dataDeValidade, Boolean utilizado) throws PrecoException, DataDeValidadeException{
    	return ingressoC.create(idEvento, idUsuario, valor, dataDeValidade, utilizado);
    }
    
    public String desenhaQRCode(String codigo){
    	return ingressoC.geraQRCode(codigo);
    }
    
    
}
