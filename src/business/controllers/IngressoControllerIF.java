package business.controllers;

import java.util.HashMap;

import util.DataDeValidadeException;
import util.PrecoException;
import util.StructureException;
import business.model.IngressoAB;

public interface IngressoControllerIF {
	public Long create (HashMap<String, Object> objeto) throws PrecoException, DataDeValidadeException, StructureException;
	public void update(Long id, HashMap<String, Object> objeto) throws PrecoException, DataDeValidadeException;
	public IngressoAB get(Long id) throws StructureException;
	public void delete(Long id);
	public IngressoAB[] listAll(Long offset, Long max) throws StructureException;
	public IngressoAB[] listAll();
}
