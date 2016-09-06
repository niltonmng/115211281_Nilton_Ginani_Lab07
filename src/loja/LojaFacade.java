/*Nilton Manuel Nogueira Ginani - Turma 03 - 115211281 - Lab07*/
package loja;

import java.util.Set;
import jogo.Jogabilidade;
import jogo.Jogo;
import usuario.Usuario;
import excecoes.DowngradeInvalidoException;
import excecoes.StringInvalidaException;
import excecoes.UpgradeInvalidoException;
import excecoes.ValorInvalidoException;

public class LojaFacade {

	private LojaController lojaController;

	public LojaFacade(){
		this.lojaController = new LojaController();
	}


	public void recompensar(String nomeJogo,int scoreObtido,boolean zerou, String login) throws ValorInvalidoException, StringInvalidaException{
		try {
			this.lojaController.recompensar(nomeJogo, scoreObtido, zerou, login);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void punir(String nomeJogo, int scoreObtido, boolean zerou,  String login) throws ValorInvalidoException, StringInvalidaException{
		try {
			this.lojaController.punir(nomeJogo, scoreObtido, zerou, login);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void cadastroUsuario(String nome, String login) {
		try {
			this.lojaController.cadastroUsuario(nome, login);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public void vendeJogo(String jogoNome, double preco, String jogabilidades, String estiloJogo, String loginUser) {
		try {
			this.lojaController.vendeJogo(jogoNome, preco, jogabilidades, estiloJogo, loginUser);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void adicionaCredito(String login, double credito) {
		try {
			this.lojaController.adicionaCredito(login, credito);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public Usuario buscaUsuario(String login) {
		try {
			return this.lojaController.buscaUsuario(login);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	public void upgrade(String login) throws UpgradeInvalidoException, StringInvalidaException {
		try {
			this.lojaController.upgrade(login);				
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void downgrade(String login) throws DowngradeInvalidoException, StringInvalidaException {
		try {
			this.lojaController.downgrade(login);				
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public double confereCredito(String login) {
		try {
			this.lojaController.confereCredito(login);
		} catch (Exception e) {
			e.getMessage();
		}
		return 0;
	}

	public String informacaoUsuarios() {
		return this.lojaController.informacaoUsuarios();				
	}

	public int getX2p(String login) {
		try {
			return this.lojaController.getX2p(login);				
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return 0;
	}
}