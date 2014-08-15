package business.controllers;

import java.util.HashMap;

import util.DataDeValidadeException;
import util.NomeException;
import business.model.EventoAB;;

public interface EventoControllerIF {
	public Long create(HashMap<String, Object> objeto) throws NomeException, DataDeValidadeException;
	public void update(Long id, HashMap<String, Object> objeto) throws NomeException, DataDeValidadeException;
	public EventoAB get(Long id);
	public void delete(Long id);
	public EventoAB[] listAll(Long offset, Long max);
	public EventoAB[] listAll();
}
