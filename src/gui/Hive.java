/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import business.controllers.BusinessFacades;
import business.controllers.Memento;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

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
 * @author ygor
 */
public class Hive {

    /**
     * @param args the command line arguments
     * @throws StructureException 
     */
    public static void main(String[] args) {
    	try {
    	insertSamples();
    	printAll();
    	
    	//BusinessFacades b = BusinessFacades.getInstance();
        
        /* 
        while(true){
            Long id = Long.parseLong(JOptionPane.showInputDialog("Digite o ID do usuário que deseja buscar:"));
            System.out.println(b.usuarioGet(id));
        }
         */
    	//} catch (StructureException ex) {
    	} catch (EmailException | IdadeException | LoginException | NomeException | SenhaException | PrecoException | StructureException | DataDeValidadeException ex) {
    		Logger.getLogger(Hive.class.getName()).log(Level.SEVERE, null, ex);
    	}
        
    }
    
    public static void printAll() throws StructureException {
    	BusinessFacades b = BusinessFacades.getInstance();
    	for (HashMap<String, Object> obj : b.usuarioListAll()) {
			System.out.println("Usuario: " + obj);
		}
        for (HashMap<String, Object> obj : b.eventoListAll()) {
			System.out.println("Evento: " + obj);
		}
        for (HashMap<String, Object> obj : b.ingressoListAll()) {
			System.out.println("Ingresso: "+ obj);
		}
    }
    
    public static void insertSamples() throws EmailException, IdadeException, LoginException, NomeException, SenhaException, StructureException, PrecoException, DataDeValidadeException {
    	BusinessFacades b = BusinessFacades.getInstance();
    	
    	//Cria usuario
        b.usuarioCreate("YgorCr", "Ygor@hotmail.com", 25, "123.456.789-11"); // id = 0
        b.usuarioCreate("YgorDiniz", "Ygor2@hotmail.com", 26, "123.456.789-12");// id = 1
        b.usuarioCreate("YgorCrA", "YgorA@hotmail.com", 25, "123.456.789-13");// id = 2
        b.usuarioCreate("YgorDinizB", "Ygor2B@hotmail.com", 26, "123.456.789-14");// id = 3 
        b.usuarioCreate("YgorCrC", "YgorC@hotmail.com", 25, "123.456.789-15");// id = 4
        b.usuarioCreate("YgorDinizD", "YgorD2@hotmail.com", 26, "123.456.789-16");// id = 5 
        b.usuarioCreate("YgorCrE", "YgorE@hotmail.com", 25, "123.456.789-17");// id = 6
        b.usuarioCreate("YgorDinizF", "Ygor2F@hotmail.com", 26, "123.456.789-18");// id = 7
   
        b.set(b.usuarioUpdate(1L, "Mudoooou", "Mudou@hotmail.com", 100, "123.456.789-16"));
        b.saveUserMemento();
        b.set(b.usuarioUpdate(1L, "Mudoudenovo", "Mudou22@hotmail.com", 2, "222.222.789-16"));
        b.undoUserMemento();
        
        //Evento
        Calendar init = Calendar.getInstance();
        Calendar fim = Calendar.getInstance();
        
        init.set(Calendar.MONTH, 11);
        fim.set(Calendar.MONTH, 12);
        
        b.eventoCreate(0L, "Showzaço", "Show legal!", "Rua dos Bobos", init, fim); //id = 0
        b.eventoCreate(1L, "Showzao", "Show massa", "Rua nao sei nao", init, fim); //id = 1
        b.eventoCreate(2L, "Showzinho", "Show fraco", "Rua do  louco", init, fim); //id = 2
        b.eventoCreate(3L, "Congresso", "QualisA", "Rua dos de baixo", init, fim); //id = 3
        b.eventoCreate(4L, "workshop", "Interessante", "Universidade", init, fim); //id = 4
        //b.eventoUpdate(0L, 1L, "Showzaoo", "nada a declarar", "rua dos bobos", init, fim);
        

        
        //Ingresso
        Calendar data = Calendar.getInstance();
        data.set(Calendar.MONTH, 11);
        String codigo = b.ingressoCreate(0L, 0L, 25.0, data, false);
        
        //Gera o QRCode
        System.out.println(codigo);
        String arqQR = b.desenhaQRCode(codigo);
        System.out.println("QRCode Salvo com nome: "+arqQR);

        codigo = b.ingressoCreate(0L, 0L, 25.0, data, false);
        codigo = b.ingressoCreate(1L, 1L, 50.0, data, false);
        codigo = b.ingressoCreate(2L, 3L, 100.0, data, false);
            
        
        
    
    }
}


