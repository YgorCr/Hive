package business.controllers;

import infra.DaoIngresso;

import java.util.Calendar;
import java.util.HashMap;

import util.DataDeValidadeException;
import util.PrecoException;
import business.model.IngressoAB;

public class IngressoController {
	
	public String create (Long idEvento, Long idUsuario, Double valor, Calendar dataDeValidade, Boolean utilizado) throws PrecoException, DataDeValidadeException{
		validaValor(valor);
		validaDataDeValidade(dataDeValidade);
		
		HashMap<String, Object> newIngresso = new HashMap<String, Object>();
        newIngresso.put("idEvento", idEvento);
        newIngresso.put("idUsuario", idUsuario);
        newIngresso.put("valor", valor);
        newIngresso.put("dataDeValidade", dataDeValidade);
        IngressoAB i = DaoIngresso.getInstance().create(newIngresso);
        return i.getCodigo();
    }
	
	public void update (Long id, Long idEvento, Long idUsuario, Double valor, Calendar dataDeValidade, Boolean utilizado) throws PrecoException, DataDeValidadeException{
		validaValor(valor);
		validaDataDeValidade(dataDeValidade);
		
		HashMap<String, Object> newIngresso = new HashMap<String, Object>();
        newIngresso.put("idEvento", idEvento);
        newIngresso.put("idUsuario", idUsuario);
        newIngresso.put("valor", valor);
        newIngresso.put("dataDeValidade", dataDeValidade);
        DaoIngresso.getInstance().update(id, get(create(idEvento, idUsuario, valor, dataDeValidade, utilizado))); //isso para nao fazer o controller conhecer o modelo
	}
	
	public void delete(Long id){
		DaoIngresso.getInstance().delete(id);
	}
	
	public IngressoAB get(String codigo){
		HashMap<String, Object> query = new HashMap<String, Object>();
    	query.put("codigo", codigo);
    	return DaoIngresso.getInstance().findOneBy(query);
	}
	
	public String geraQRCode(String codigo){
		CodigoIF gerador = new CodigoAdapter();
		
		return gerador.geraQRCode(codigo);
	}
	
	public void validaValor(Double valor) throws PrecoException{
		if(valor < 0){
			throw new PrecoException("O Valor do Ingresso deve ser positivo!");
		}
		
	}
	
	public void validaDataDeValidade(Calendar dataDeValidade) throws DataDeValidadeException{ //determinar hora minuto e segundo da validade de um ingresso
		if((dataDeValidade != null) && dataDeValidade.before(Calendar.getInstance())){
			throw new DataDeValidadeException("A Data de Validade do Ingresso já foi ultrapassada!");
		}
	}
	
}
