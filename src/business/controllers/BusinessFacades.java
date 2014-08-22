/*
 * Padrão: Facade
 * Pardão: Singleton
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
 * @author igor
 */
public class BusinessFacades {
    private static BusinessFacades business;
        
    private BusinessFacades() {
    }
   
    //Singleton
    public static BusinessFacades getInstance(){
        if(business == null){
            business = new BusinessFacades();
        }
        return business;
    }
    
    /*
     * Métodos de usuario - CRUD
     */
    public void usuarioCreate(String nome, String email, int idade, String cpf) throws EmailException, IdadeException, LoginException, NomeException, SenhaException{
       CommandUsuarioCreate commandUC = new CommandUsuarioCreate(nome, email, idade, cpf);
       commandUC.execute();
    }
    
    public HashMap<String, Object> usuarioGet(Long id){
        UsuarioController userC = new UsuarioController();
        return userC.toHashMap(userC.get(id));
    }
    
    public List<HashMap<String, Object>> usuarioListAll(){
    	UsuarioController userC = new UsuarioController();
    	return userC.listToHashMap(userC.listAll());
    }
    
    public void usuarioUpdate(Long id, String nome, String email, int idade, String cpf) throws EmailException, IdadeException, LoginException, NomeException, SenhaException, PrecoException, DataDeValidadeException{
    	CommandUsuarioUpdate commandUP = new CommandUsuarioUpdate(id, nome, email, idade, cpf);
    	commandUP.execute();
    }
    
    public void usuarioDelete(Long id){
    	UsuarioController userC = new UsuarioController();
    	userC.delete(id);
    }
    
    /*
     * Métodos de Ingresso - CRUD
     * Gerar QRCode
     */
    
    public String desenhaQRCode(String codigo){
    	IngressoController ingressoC = new IngressoController();
    	return ingressoC.geraQRCode(codigo);
    }
        
    public String ingressoCreate(Long idEvento, Long idUsuario, Double valor, Calendar dataDeValidade, Boolean utilizado) throws PrecoException, DataDeValidadeException, EmailException, IdadeException, LoginException, NomeException, SenhaException{
    	CommandIngressoCreate commandIC = new CommandIngressoCreate(idEvento, idUsuario, valor, dataDeValidade, utilizado);
    	commandIC.execute();
    	return Long.toString(commandIC.getIdCode());
    }
    
    public HashMap<String, Object> ingressoGet(Long id){
    	IngressoController ingressoC = new IngressoController();
    	return ingressoC.toHashMap(ingressoC.get(id));
    }
    
    public List<HashMap<String, Object>> ingressoListAll(){
    	IngressoController ingressoC = new IngressoController();
    	return ingressoC.listToHashMap(ingressoC.listAll());
    }

    public void ingressoUpdate(Long idIngresso, Long idEvento, Long idUsuario, Double valor, Calendar dataDeValidade, Boolean utilizado) throws PrecoException, DataDeValidadeException, EmailException, IdadeException, LoginException, NomeException, SenhaException{
    	CommandIngressoUpdate commandIU = new CommandIngressoUpdate(idIngresso, idEvento, idUsuario, valor, dataDeValidade, utilizado);
    	commandIU.execute();
    }

    public void IngressoDelete(Long id){
    	IngressoController ingressoC = new IngressoController();
    	ingressoC.delete(id);
    }
    
    /*
     * Métodos de Evento - CRUD
     */
    
    public void eventoCreate(Long idDono, String nome, String descricao, String endereco, Calendar dataInicio, Calendar dataFim) throws PrecoException, DataDeValidadeException, NomeException, EmailException, IdadeException, LoginException, SenhaException{
    	CommandEventoCreate commandEC = new CommandEventoCreate(idDono, nome, descricao, endereco, dataInicio, dataFim);
    	commandEC.execute();
    }
    
    public HashMap<String, Object> eventoGet(Long id){
    	EventoController eventoC = new EventoController();
    	return eventoC.toHashMap(eventoC.get(id));
    }
    
    public List<HashMap<String, Object>> eventoListAll(){
    	EventoController eventoC = new EventoController();
    	return eventoC.listToHashMap(eventoC.listAll());
    }
    
    public void eventoUpdate(Long idEvento,Long idDono, String nome, String descricao, String endereco, Calendar dataInicio, Calendar dataFim) throws PrecoException, DataDeValidadeException, NomeException, EmailException, IdadeException, LoginException, SenhaException{
    	CommandEventoUpdate commandEU = new CommandEventoUpdate(idEvento, idDono, nome, descricao, endereco, dataInicio, dataFim);
    	commandEU.execute();
    }
        
    public void eventoDelete(Long id){
    	EventoController eventoC = new EventoController();
    	eventoC.delete(id);
    }
}


