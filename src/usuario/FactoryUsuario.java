/*Nilton Manuel Nogueira Ginani - Turma 03 - 115211281 - Lab07*/
package usuario;

import excecoes.StringInvalidaException;

public class FactoryUsuario {
	
	public Usuario criaUsuario(String nome, String login, String tipo) throws StringInvalidaException {
		Usuario usuario = null;
		if(tipo.equalsIgnoreCase("noob")){
			usuario = new Noob(nome, login);
		}
		if(tipo.equalsIgnoreCase("veterano")){
			usuario = new Veterano(nome, login);
		}
		return usuario;
	}
}