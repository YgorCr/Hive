package business.controllers;

import infra.DaoAbstractFactory;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import util.DataDeValidadeException;
import util.NomeException;
import business.model.EventoAB;

public class EventoController implements EventoControllerIF{

	@Override
	public Long create(HashMap<String, Object> objeto) throws NomeException, DataDeValidadeException {
		validaNome((String)objeto.get("nome"));
		validaDataDeValidade(objeto.get("dataInicio"));
		validaDataDeValidade(objeto.get("dataFim"));

		EventoAB newEvent = (EventoAB) DaoAbstractFactory.getInstance(EventoAB.class).create(objeto);
        
		return newEvent.getId();
	}

	@Override
	public void update(Long id, HashMap<String, Object> objeto) throws NomeException, DataDeValidadeException {
		validaNome((String)objeto.get("nome"));
		validaDataDeValidade(objeto.get("dataInicio"));
		validaDataDeValidade(objeto.get("dataFim"));
		
		DaoAbstractFactory.getInstance(EventoAB.class).update(id, objeto);
	}

	@Override
	public EventoAB get(Long id) {
    	return (EventoAB) DaoAbstractFactory.getInstance(EventoAB.class).get(id);
	}

	@Override
	public void delete(Long id) {
		DaoAbstractFactory.getInstance(EventoAB.class).delete(id);
	}

	@Override
	public EventoAB[] listAll(Long offset, Long max) {
		List<?> all = DaoAbstractFactory.getInstance(EventoAB.class).listAll(max, offset);
    	return (EventoAB[]) all.toArray(new EventoAB[all.size()]);
	}

	@Override
	public EventoAB[] listAll() {
		List<?> all = DaoAbstractFactory.getInstance(EventoAB.class).listAll();
    	return (EventoAB[]) all.toArray(new EventoAB[all.size()]);
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
	
	public void validaDataDeValidade(Object dataDeValidade1) throws DataDeValidadeException{ //determinar hora minuto e segundo da validade de um ingresso
		Calendar dataDeValidade = (Calendar) dataDeValidade1;
		if((dataDeValidade != null) && dataDeValidade.before(Calendar.getInstance())){
			throw new DataDeValidadeException("A Data de Validade do Ingresso já foi ultrapassada!");
		}
	}
}
