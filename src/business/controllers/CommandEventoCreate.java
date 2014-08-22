package business.controllers;

import java.util.Calendar;
import java.util.HashMap;

import util.DataDeValidadeException;
import util.EmailException;
import util.IdadeException;
import util.LoginException;
import util.NomeException;
import util.SenhaException;
import util.StructureException;

public class CommandEventoCreate implements Command{
	
	private HashMap<String, Object> objeto = new HashMap<String, Object>();
	
	public CommandEventoCreate(Long idDono, String nome, String descricao, String endereco, Calendar dataInicio, Calendar dataFim){
		objeto.put("idDono", idDono);
        objeto.put("nome", nome);
        objeto.put("descricao", descricao);
        objeto.put("endereco", endereco);
        objeto.put("dataInicio", dataInicio);
        objeto.put("dataFim", dataFim);
	}

	@Override
	public void execute() throws EmailException, IdadeException,
			LoginException, NomeException, SenhaException, DataDeValidadeException, StructureException {
    	EventoController eventoC = new EventoController();
    	eventoC.create(objeto);
		
	}

}
