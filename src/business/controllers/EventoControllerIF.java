package business.controllers;

import java.util.HashMap;

import util.DataDeValidadeException;
import util.NomeException;
import util.StructureException;
import business.model.EventoAB;;

public interface EventoControllerIF {
	public Long create(HashMap<String, Object> objeto) throws NomeException, DataDeValidadeException, StructureException;
	public void update(Long id, HashMap<String, Object> objeto) throws NomeException, DataDeValidadeException, StructureException;
	public EventoAB get(Long id) throws StructureException;
	public void delete(Long id) throws StructureException;
	public EventoAB[] listAll(Long offset, Long max) throws StructureException;
	public EventoAB[] listAll() throws StructureException;
}
