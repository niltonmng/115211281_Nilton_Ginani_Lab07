package usuario;

import jogo.Jogo;
import excecoes.StringInvalidaException;
import excecoes.ValorInvalidoException;

public interface TipoDeUsuarioIF {

	public abstract void compraJogo(Jogo jogo) throws ValorInvalidoException;
	public abstract void recompensar(String nomeJogo,int scoreObtido,boolean zerou) throws ValorInvalidoException, StringInvalidaException;
	public abstract void punir(String nomeJogo, int scoreObtido, boolean zerou) throws ValorInvalidoException, StringInvalidaException;
}
