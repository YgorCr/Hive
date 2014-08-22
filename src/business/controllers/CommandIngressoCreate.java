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
import util.StructureException;

public class CommandIngressoCreate implements Command{

	private HashMap<String, Object> objeto = new HashMap<String, Object>();
	private Long idCode;
	
	public CommandIngressoCreate(Long idEvento, Long idUsuario, Double valor, Calendar dataDeValidade, Boolean utilizado) {
        objeto.put("idEvento", idEvento);
        objeto.put("idUsuario", idUsuario);
        objeto.put("valor", valor);
        objeto.put("dataDeValidade", dataDeValidade);
        objeto.put("utilizado", utilizado);	}
	
	@Override
	public void execute() throws EmailException, IdadeException,
			LoginException, NomeException, SenhaException, PrecoException, DataDeValidadeException, StructureException {
		
    	IngressoController ingressoC = new IngressoController();
    	idCode = ingressoC.create(objeto);
	}
	
	public Long getIdCode(){
		return idCode;
	}

}
