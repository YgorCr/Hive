/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import business.controllers.BusinessFacades;

import java.util.Calendar;
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

/**
 *
 * @author ygor
 */
public class Hive {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    	
        BusinessFacades b = BusinessFacades.getInstance();
        try {
            b.usuarioCreate("YgorCr", "Ygor@hotmail.com", 25, "123.456.789-11"); // id = 0
            b.usuarioCreate("YgorDiniz", "Ygor2@hotmail.com", 26, "123.456.789-12");// id = 1
            b.usuarioCreate("YgorCrA", "YgorA@hotmail.com", 25, "123.456.789-13");// id = 2
            b.usuarioCreate("YgorDinizB", "Ygor2B@hotmail.com", 26, "123.456.789-14");// id = 3 
            b.usuarioCreate("YgorCrC", "YgorC@hotmail.com", 25, "123.456.789-15");// id = 4
            b.usuarioCreate("YgorDinizD", "YgorD2@hotmail.com", 26, "123.456.789-16");// id = 5 
            b.usuarioCreate("YgorCrE", "YgorE@hotmail.com", 25, "123.456.789-17");// id = 6
            b.usuarioCreate("YgorDinizF", "Ygor2F@hotmail.com", 26, "123.456.789-18");// id = 7
            
            Calendar data = Calendar.getInstance();
            data.set(Calendar.MONTH, 11);
            String codigo = b.ingressoCreate(0L, 0L, 25.0, data, false);
            System.out.println(codigo);
            String arqQR = b.desenhaQRCode(codigo);
            System.out.println("QRCode Salvo com nome: "+arqQR);

            
        } catch (EmailException | IdadeException | LoginException | NomeException | SenhaException | PrecoException | DataDeValidadeException ex) {
            Logger.getLogger(Hive.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        /* */
        while(true){
            Long id = Long.parseLong(JOptionPane.showInputDialog("Digite o ID do usuário que deseja buscar:"));
            System.out.println(b.usuarioGet(id));
        }
        /* */
        
        /* *	
        for (HashMap<String, Object> obj : b.usuarioListAll()) {
			System.out.println(obj);
		}
        /**/
        
    }
    
}
