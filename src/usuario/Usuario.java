package usuario;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import subclassesJogo.JogoLuta;
import subclassesJogo.JogoPlataforma;
import subclassesJogo.JogoRPG;

import excecoes.StringInvalidaException;
import excecoes.ValorInvalidoException;
import jogo.*;

public abstract class Usuario {

	public static final String FIM_DE_LINHA = System.lineSeparator();

	private String nome;
	private String login;
	private Set<Jogo> meusJogos;
	private double credito;
	private int xp2;

	public Usuario(String nome, String login) throws StringInvalidaException {

		if (nome == null || nome.trim().isEmpty()) {
			throw new StringInvalidaException("Nome nao pode ser nulo ou vazio.");
		}
		if (login == null || login.trim().isEmpty()) {
			throw new StringInvalidaException("Login nao pode ser nulo ou vazio.");
		}

		this.nome = nome;
		this.login = login;
		meusJogos = new HashSet<Jogo>();
		this.credito = 0;
	}

	public abstract void compraJogo(Jogo jogo) throws ValorInvalidoException;
	
	public abstract void recompensar(String nomeJogo,int scoreObtido,boolean zerou) throws ValorInvalidoException, StringInvalidaException;
	
	public abstract void punir(String nomeJogo, int scoreObtido, boolean zerou) throws ValorInvalidoException, StringInvalidaException;

	public void setXp2(int novoValor) {
		this.xp2 = novoValor;
	}

	public int getXp2() {
		return this.xp2;
	}

	public void cadastraJogo(Jogo jogo) {
		this.meusJogos.add(jogo);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setCredito(double novoValor) {
		this.credito = novoValor;
	}

	public double getCredito() {
		return this.credito;
	}

	/* Reusei este codigo nas subclasses.
	public void registradaJogada(String nomeJogo, int score, boolean venceu) throws Exception {
		Jogo jogo = this.buscaJogo(nomeJogo);
		if (jogo == null) {
			throw new Exception();
		}
		setXp2(getXp2() + jogo.registraJogada(score, venceu));
	}*/	
	
	public Jogo buscaJogo(String nomeJogo) {
		Jogo buscado = null;
		Iterator itr = meusJogos.iterator();
		while (itr.hasNext()) {
			Jogo achado = (Jogo) itr.next();
			if (achado.getNome().equals(nomeJogo)) {
				buscado = achado;
			}
		}
		return buscado;
	}

	public Set<Jogo> getMeusJogos() {
		return meusJogos;
	}

	public void setMeusJogos(Set<Jogo> meusJogos) {
		this.meusJogos = meusJogos;
	}

	public double calculaPrecoTotal() {
		double total = 0;
		Iterator itr = meusJogos.iterator();
		while (itr.hasNext()) {
			Jogo achado = (Jogo) itr.next();
			total += achado.getPreco();
		}
		return total;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Usuario) {
			Usuario temp = (Usuario) obj;
			return this.getNome().equals(temp.getNome()) && this.getLogin().equals(temp.getLogin());
		} else {
			return false;
		}
	}
	
	public String toString(){
		final String QUEBRA_LINHA = System.lineSeparator();
		String tipoJogo = "";
		String retorno = "";
		double acumulaPreco = 0;
		for(Jogo jogo : this.getMeusJogos()){
			acumulaPreco += jogo.getPreco();
			if(jogo instanceof Luta){
				tipoJogo += "Luta";
			}
			else if(jogo instanceof Rpg){
				tipoJogo += "RPG";
			}
			else if(jogo instanceof Plataforma){
				tipoJogo += "Plataforma";
			}
			retorno +="+ " + jogo.getNome() + " - " + tipoJogo + ":" + QUEBRA_LINHA
					+ "==> Jogou " + jogo.getVezesJogadas() + " vez(es)" + QUEBRA_LINHA
					+ "==> Zerou " + jogo.getvezesConcluidas() + " vez(es)" + QUEBRA_LINHA
					+ "==> Maior score: " + jogo.getMaiorScore() + QUEBRA_LINHA
					+ QUEBRA_LINHA;
			tipoJogo = "";
		}
		retorno += "Total de pre�o dos jogos: R$ " + acumulaPreco + QUEBRA_LINHA + QUEBRA_LINHA
				+ "--------------------------------------------" + QUEBRA_LINHA + QUEBRA_LINHA;
		return retorno;
	}
}
