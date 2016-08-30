package jogo;

import java.util.Set;
import excecoes.StringInvalidaException;
import excecoes.PrecoInvalidoException;
import excecoes.ValorInvalidoException;

public class FactoryJogo {

	
	public Jogo criaJogo(String nome, double preco, Set<Jogabilidade> jogabilidades, String tipo) throws StringInvalidaException, PrecoInvalidoException, ValorInvalidoException {
		Jogo jogo = null;
		if(tipo.equalsIgnoreCase("plataforma")){
			jogo = new Plataforma(nome, preco);
		}
		if(tipo.equalsIgnoreCase("rpg")){
			jogo = new Rpg(nome, preco);
		}
		if(tipo.equalsIgnoreCase("luta")){
			jogo = new Luta(nome, preco);
		}
		return jogo;
	}
	
}
