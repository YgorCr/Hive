package business.controllers;

import java.util.HashMap;

import util.DataDeValidadeException;
import util.PrecoException;
import business.model.IngressoAB;

public interface IngressoControllerIF {
	public Long create (HashMap<String, Object> objeto) throws PrecoException, DataDeValidadeException;
	public void update(Long id, HashMap<String, Object> objeto) throws PrecoException, DataDeValidadeException;
	public IngressoAB get(Long id);
	public void delete(Long id);
	public IngressoAB[] listAll(Long offset, Long max);
	public IngressoAB[] listAll();
}
