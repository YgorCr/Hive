package business.controllers;

import infra.DaoAbstractFactory;
import infra.DaoIngresso;

import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;

import util.DataDeValidadeException;
import util.PrecoException;
import business.model.IngressoAB;

public class IngressoController implements IngressoControllerIF{
	
	public Long create (HashMap<String, Object> objeto) throws PrecoException, DataDeValidadeException{
		validaValor(objeto.get("valor"));
		validaDataDeValidade(objeto.get("dataDeValidade"));
		IngressoAB i = (IngressoAB) DaoAbstractFactory.getInstance(IngressoAB.class).create(objeto);
        return i.getId();
    }
	
	public void update (Long id, HashMap<String, Object> objeto) throws PrecoException, DataDeValidadeException{
		validaValor(objeto.get("valor"));
		validaDataDeValidade(objeto.get("dataDeValidade"));
		
		DaoAbstractFactory.getInstance(IngressoAB.class).update(id, objeto);
	}
	
	public void delete(Long id){
		DaoAbstractFactory.getInstance(IngressoAB.class).delete(id);
	}
	
	public IngressoAB get(Long id){
		HashMap<String, Object> query = new HashMap<String, Object>();
    	query.put("id", id);
    	return (IngressoAB) DaoAbstractFactory.getInstance(IngressoAB.class).findOneBy(query);
	}
	
	public String geraQRCode(String codigo){
		CodigoIF gerador = new CodigoAdapter();
		
		return gerador.geraQRCode(codigo);
	}
	
	public void validaValor(Object valor) throws PrecoException{
		
		if((Double) valor < 0){
			throw new PrecoException("O Valor do Ingresso deve ser positivo!");
		}
		
	}
	
	public void validaDataDeValidade(Object dataDeValidade1) throws DataDeValidadeException{ //determinar hora minuto e segundo da validade de um ingresso
		Calendar dataDeValidade = (Calendar) dataDeValidade1;
		if((dataDeValidade != null) && dataDeValidade.before(Calendar.getInstance())){
			throw new DataDeValidadeException("A Data de Validade do Ingresso já foi ultrapassada!");
		}
	}
		
	@Override
	public IngressoAB[] listAll(Long offset, Long max) {
		// TODO Auto-generated method stub
		 return Arrays.copyOf(DaoAbstractFactory.getInstance(IngressoAB.class).listAll(offset, max).toArray(),DaoAbstractFactory.getInstance(IngressoAB.class).listAll().toArray().length,IngressoAB[].class);
	}

	@Override
	public IngressoAB[] listAll() {
		// TODO Auto-generated method stub
		return Arrays.copyOf(DaoAbstractFactory.getInstance(IngressoAB.class).listAll().toArray(),DaoAbstractFactory.getInstance(IngressoAB.class).listAll().toArray().length,IngressoAB[].class);
	}
	
}
