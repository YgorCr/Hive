package business.controllers;

import java.util.HashMap;

import util.EmailException;
import util.IdadeException;
import util.LoginException;
import util.NomeException;
import util.SenhaException;

public class CommandUsuarioCreate implements Command{
	
	private HashMap<String, Object> objeto = new HashMap<String, Object>();
	
	public CommandUsuarioCreate(String nome, String email, int idade, String cpf) {
        objeto.put("nome", nome);
        objeto.put("email", email);
        objeto.put("idade", idade);
        objeto.put("cpf", cpf);
	}
	@Override
	public void execute() throws EmailException, IdadeException, LoginException, NomeException, SenhaException {
		UsuarioController userC = new UsuarioController();
		userC.create(objeto);
	}

}
