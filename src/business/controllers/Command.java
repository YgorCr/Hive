package business.controllers;

import util.DataDeValidadeException;
import util.EmailException;
import util.IdadeException;
import util.LoginException;
import util.NomeException;
import util.PrecoException;
import util.SenhaException;
/*
 * Padrão: Command
 */


public interface Command {
	public void execute() throws EmailException, IdadeException, LoginException, NomeException, SenhaException, PrecoException, DataDeValidadeException;
}
