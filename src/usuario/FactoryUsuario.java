package usuario;

import excecoes.StringInvalidaException;

public class FactoryUsuario {
	
	public Usuario criaNoob(String nome, String login) throws StringInvalidaException {
		return new Noob(nome, login);
	}
	
	public Usuario criaVeterano(String nome, String login) throws StringInvalidaException {
		return new Veterano(nome, login);
	}
}
