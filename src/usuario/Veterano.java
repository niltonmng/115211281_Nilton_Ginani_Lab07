/*Nilton Manuel Nogueira Ginani - Turma 03 - 115211281 - Lab07*/
package usuario;

import java.util.Iterator;

import enumerations.Jogabilidade;
import excecoes.StringInvalidaException;
import excecoes.ValorInvalidoException;
import jogo.Jogo;

public class Veterano implements TipoDeUsuarioIF {

	public static final double DESCONTO_VETERANO = 0.8;

	public double compraJogo(Jogo jogo){
		double custo = jogo.getPreco() * DESCONTO_VETERANO; 
		return custo;
	}
	
	public int recompensar(Jogo jogo) {
		int novoX2P = 0;
		if(jogo.getJogabilidades().contains(Jogabilidade.ONLINE)){
			novoX2P += 10;
		}
		if(jogo.getJogabilidades().contains(Jogabilidade.COOPERATIVO)){
			novoX2P += 20;
		}
		return novoX2P;
	}

	public int punir(Jogo jogo) {		
		int novoX2P = 0;
		if(jogo.getJogabilidades().contains(Jogabilidade.OFFLINE)){ // forma de elementos do tipo enum.
			novoX2P += 20;
		}
		if(jogo.getJogabilidades().contains(Jogabilidade.COMPETITIVO)){
			novoX2P += 20;
		}
		return novoX2P;
	}

	/*@Override
	public String toString() {
		String myString = this.getLogin() + FIM_DE_LINHA;
		myString += this.getNome() + " - Jogador Veterano" + FIM_DE_LINHA;
		myString += "Lista de Jogos:" + FIM_DE_LINHA;

		Iterator itr = getMeusJogos().iterator();
		while (itr.hasNext()) {
			Jogo j = (Jogo) itr.next();
			myString += j.toString();
		}
		myString += FIM_DE_LINHA;
		myString += "Total de pre�o dos jogos: R$ " + this.calculaPrecoTotal() + FIM_DE_LINHA;
		myString += "--------------------------------------------";
		return myString;
	}
	
	/*public Veterano(String nome, String login) throws StringInvalidaException { seria feito desta forma se fosse com heranca
		super(nome, login);
		setXp2(1000);
	}
	
	public void compraJogo(Jogo jogo) throws ValorInvalidoException {
		double custo = jogo.getPreco() * DESCONTO_VETERANO;
		if (custo > this.getCredito()) {
			throw new ValorInvalidoException("Credito insuficiente para realizar a compra.");
		} else {
			int parteInteira =(int)( jogo.getPreco() - (jogo.getPreco() % 1));
			int bonusXp =  parteInteira * 10;
			setXp2(getXp2() + bonusXp);
			setCredito(getCredito() - custo);
			this.cadastraJogo(jogo);

		}
	}

	public void recompensar(String nomeJogo, int scoreObtido, boolean zerou)
			throws ValorInvalidoException, StringInvalidaException {
		if (nomeJogo == null || nomeJogo.trim().isEmpty()){
			throw new StringInvalidaException("Nome do jogo nao pode ser vazio ou nulo.");
		}
		if(scoreObtido <= 0){
			throw new ValorInvalidoException("A pontuacao nao pode ser menor ou igual a zero.");
		}
		
		Jogo jogo = this.buscaJogo(nomeJogo);
		this.setXp2(this.getXp2() + jogo.registraJogada(scoreObtido, zerou));
		
		int novoX2P = 0;
		if(this.buscaJogo(nomeJogo).getJogabilidades().contains(Jogabilidade.ONLINE)){
			novoX2P += 10;
		}
		if(this.buscaJogo(nomeJogo).getJogabilidades().contains(Jogabilidade.COOPERATIVO)){
			novoX2P += 20;
		}
		this.setXp2(this.getXp2() + novoX2P);
	}

	public void punir(String nomeJogo, int scoreObtido, boolean zerou)
			throws ValorInvalidoException, StringInvalidaException {
		if (nomeJogo == null || nomeJogo.trim().isEmpty()){
			throw new StringInvalidaException("Nome do jogo nao pode ser vazio ou nulo.");
		}
		if(scoreObtido <= 0){
			throw new ValorInvalidoException("A pontuacao nao pode ser menor ou igual a zero.");
		}
		
		Jogo jogo = this.buscaJogo(nomeJogo);
		this.setXp2(this.getXp2() + jogo.registraJogada(scoreObtido, zerou));
		
		int novoX2P = 0;
		if(this.buscaJogo(nomeJogo).getJogabilidades().contains(Jogabilidade.OFFLINE)){ // forma de elementos do tipo enum.
			novoX2P += 20;
		}
		if(this.buscaJogo(nomeJogo).getJogabilidades().contains(Jogabilidade.COMPETITIVO)){
			novoX2P += 20;
		}
		this.setXp2(this.getXp2() - novoX2P);
	}

	@Override
	public String toString() {
		String myString = this.getLogin() + FIM_DE_LINHA;
		myString += this.getNome() + " - Jogador Veterano" + FIM_DE_LINHA;
		myString += "Lista de Jogos:" + FIM_DE_LINHA;

		Iterator itr = getMeusJogos().iterator();
		while (itr.hasNext()) {
			Jogo j = (Jogo) itr.next();
			myString += j.toString();
		}
		myString += FIM_DE_LINHA;
		myString += "Total de pre�o dos jogos: R$ " + this.calculaPrecoTotal() + FIM_DE_LINHA;
		myString += "--------------------------------------------";
		return myString;
	}*/
}