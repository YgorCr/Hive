package business.controllers;

import java.util.HashMap;

import util.DataDeValidadeException;
import util.EmailException;
import util.IdadeException;
import util.LoginException;
import util.NomeException;
import util.PrecoException;
import util.SenhaException;

public class CommandUsuarioUpdate implements Command{
	
	private Long id;
	private HashMap<String, Object> objeto = new HashMap<String, Object>();

	public CommandUsuarioUpdate(Long id, String nome, String email, int idade, String cpf){
        objeto.put("nome", nome);
        objeto.put("email", email);
        objeto.put("idade", idade);
        objeto.put("cpf", cpf);
        this.id = id;
	}

	@Override
	public void execute() throws EmailException, IdadeException,
			LoginException, NomeException, SenhaException, PrecoException,
			DataDeValidadeException {
    	UsuarioController userC = new UsuarioController();
        userC.update(id, objeto);
	}

}
