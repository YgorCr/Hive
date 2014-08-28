/*
 * Padrão: Facade
 * Pardão: Singleton
 */

package business.controllers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import com.sun.org.apache.bcel.internal.generic.NEW;

import util.DataDeValidadeException;
import util.EmailException;
import util.IdadeException;
import util.LoginException;
import util.NomeException;
import util.PrecoException;
import util.SenhaException;
import util.StructureException;

/**
 *
 * @author igor
 */
public class BusinessFacades {
    private static BusinessFacades business;

    private List<Memento> savedStates = new ArrayList<Memento>();
    private HashMap<String, Object> state;

        
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
    public void usuarioCreate(String nome, String email, int idade, String cpf) throws EmailException, IdadeException, LoginException, NomeException, SenhaException, StructureException{
       CommandUsuarioCreate commandUC = new CommandUsuarioCreate(nome, email, idade, cpf);
       commandUC.execute();
    }
    
    public HashMap<String, Object> usuarioGet(Long id) throws StructureException{
        UsuarioController userC = new UsuarioController();
        return userC.toHashMap(userC.get(id));
    }
    
    public List<HashMap<String, Object>> usuarioListAll() throws StructureException{
    	UsuarioController userC = new UsuarioController();
    	return userC.listToHashMap(userC.listAll());
    }
    
    public HashMap<String, Object> usuarioUpdate(Long id, String nome, String email, int idade, String cpf) throws EmailException, IdadeException, LoginException, NomeException, SenhaException, PrecoException, DataDeValidadeException, StructureException{
    	CommandUsuarioUpdate commandUP = new CommandUsuarioUpdate(id, nome, email, idade, cpf);
    	commandUP.execute();
    	return commandUP.getObjeto();
    }
    
    public void usuarioDelete(Long id) throws StructureException{
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
        
    public String ingressoCreate(Long idEvento, Long idUsuario, Double valor, Calendar dataDeValidade, Boolean utilizado) throws PrecoException, DataDeValidadeException, EmailException, IdadeException, LoginException, NomeException, SenhaException, StructureException{
    	CommandIngressoCreate commandIC = new CommandIngressoCreate(idEvento, idUsuario, valor, dataDeValidade, utilizado);
    	commandIC.execute();
    	return Long.toString(commandIC.getIdCode());
    }
    
    public HashMap<String, Object> ingressoGet(Long id) throws StructureException{
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

    public void IngressoDelete(Long id) throws StructureException{
    	IngressoController ingressoC = new IngressoController();
    	ingressoC.delete(id);
    }
    
    /*
     * Métodos de Evento - CRUD
     */
    
    public void eventoCreate(Long idDono, String nome, String descricao, String endereco, Calendar dataInicio, Calendar dataFim) throws PrecoException, DataDeValidadeException, NomeException, EmailException, IdadeException, LoginException, SenhaException, StructureException{
    	CommandEventoCreate commandEC = new CommandEventoCreate(idDono, nome, descricao, endereco, dataInicio, dataFim);
    	commandEC.execute();
    }
    
    public HashMap<String, Object> eventoGet(Long id) throws StructureException{
    	EventoController eventoC = new EventoController();
    	return eventoC.toHashMap(eventoC.get(id));
    }
    
    public List<HashMap<String, Object>> eventoListAll() throws StructureException{
    	EventoController eventoC = new EventoController();
    	return eventoC.listToHashMap(eventoC.listAll());
    }
    
    public void eventoUpdate(Long idEvento,Long idDono, String nome, String descricao, String endereco, Calendar dataInicio, Calendar dataFim) throws PrecoException, DataDeValidadeException, NomeException, EmailException, IdadeException, LoginException, SenhaException, StructureException{
    	CommandEventoUpdate commandEU = new CommandEventoUpdate(idEvento, idDono, nome, descricao, endereco, dataInicio, dataFim);
    	commandEU.execute();
    }
        
    public void eventoDelete(Long id) throws StructureException{
    	EventoController eventoC = new EventoController();
    	eventoC.delete(id);
    }

    
    //Memento funcionando
    public void set(HashMap<String, Object> state) {
        System.out.println("Setando o estado " + state);
        this.state = state;
    }
 
    private Memento saveToMemento() {
        System.out.println("Salvando no Memento");
        return new Memento(state);
    }
 
    private void undoMemento(Memento memento) {
        state = memento.getSavedState();
        System.out.println("Estado após Undo " + state);
    }
    
    public void saveUserMemento(){
    	savedStates.add(saveToMemento());
    }
    
    public void undoUserMemento() throws EmailException, IdadeException, LoginException, NomeException, SenhaException, PrecoException, DataDeValidadeException, StructureException{
    	int atual = savedStates.size()-1;
        undoMemento(savedStates.get(atual));
    
        UsuarioController uc = new UsuarioController();
        uc.update((Long)state.get("id"), state);
    }
    
}


