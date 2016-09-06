/*Nilton Manuel Nogueira Ginani - Turma 03 - 115211281 - Lab07*/
package usuario;

import jogo.Jogo;
import excecoes.StringInvalidaException;
import excecoes.ValorInvalidoException;

public interface TipoDeUsuarioIF {

	public abstract double compraJogo(Jogo jogo);
	public abstract int recompensar(Jogo jogo);
	public abstract int punir(Jogo jogo);
}
