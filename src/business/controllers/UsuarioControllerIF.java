package business.controllers;

import java.util.HashMap;

import util.*;
import business.model.UsuarioAB;

public interface UsuarioControllerIF {
	public Long create(HashMap<String, Object> objeto) throws EmailException, IdadeException, LoginException, NomeException, SenhaException;
	public void update(Long id, HashMap<String, Object> objeto) throws EmailException, IdadeException, LoginException, NomeException, SenhaException, StructureException;
	public UsuarioAB get(Long id) throws StructureException;
	public void delete(Long id) throws StructureException;
	public UsuarioAB[] listAll(Long offset, Long max) throws StructureException;
	public UsuarioAB[] listAll() throws StructureException;
}
