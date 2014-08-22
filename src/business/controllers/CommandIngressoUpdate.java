package business.controllers;

import java.util.Calendar;
import java.util.HashMap;

import util.DataDeValidadeException;
import util.EmailException;
import util.IdadeException;
import util.LoginException;
import util.NomeException;
import util.PrecoException;
import util.SenhaException;

public class CommandIngressoUpdate implements Command{
	private Long id;
	private HashMap<String, Object> objeto = new HashMap<String, Object>();
	
	public CommandIngressoUpdate(Long idIngresso, Long idEvento, Long idUsuario, Double valor, Calendar dataDeValidade, Boolean utilizado){
        objeto.put("idEvento", idEvento);
        objeto.put("idUsuario", idUsuario);
        objeto.put("valor", valor);
        objeto.put("dataDeValidade", dataDeValidade);
        objeto.put("utilizado", utilizado);
    	this.id = idIngresso;
	}
	@Override
	public void execute() throws EmailException, IdadeException,
			LoginException, NomeException, SenhaException, PrecoException,
			DataDeValidadeException {
    	IngressoController ingressoC = new IngressoController();
    	ingressoC.update(id, objeto);		
	}

}
