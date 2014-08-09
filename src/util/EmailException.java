/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package util;

/**
 *
 * @author ygor
 */
public class EmailException extends Exception{
   
	private static final long serialVersionUID = 9186792398037554350L;

	public EmailException(String message){
        super(message);
    }
}
