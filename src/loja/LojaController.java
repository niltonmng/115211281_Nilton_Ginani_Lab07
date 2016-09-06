package loja;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import easyaccept.EasyAccept;
import excecoes.*;
import jogo.*;
import usuario.Usuario;

public class LojaController {
	
	public static final String FIM_DE_LINHA = System.lineSeparator();
	private List<Usuario> meusUsuarios;
	private HashMap<String, Jogabilidade> mapJogabildades;
	private FactoryJogo factoryJogo;
	//private FactoryUsuario factoryUsuario;

	public LojaController() {
		this.meusUsuarios = new ArrayList<Usuario>();
		this.initializeMap();
		this.factoryJogo = new FactoryJogo();
		//this.factoryUsuario = new FactoryUsuario();
	}


	// nao necessia criar o usuario com a factory, pois o que muda é seu status, e nao um usuario de tipo diferente.
	public void cadastroUsuario(String nome, String login /*, String tipo*/ ) throws StringInvalidaException {
		//Usuario novoUser = this.factoryUsuario.criaUsuario(nome, login, tipo);
		Usuario novoUser = new Usuario(nome, login);
		this.meusUsuarios.add(novoUser);
	}

	// feito a parte de venda de jogo.
	public void vendeJogo(String jogoNome, double preco, String jogabilidades, String estiloJogo, String loginUser) 
			throws StringInvalidaException, PrecoInvalidoException, ValorInvalidoException {
		Usuario buscado = this.buscaUsuario(loginUser);
		Set<Jogabilidade> tiposJogabilidades = this.createJogabilidades(jogabilidades);
		Jogo jogoVendido = this.criaJogo(jogoNome, preco, tiposJogabilidades, estiloJogo);
		buscado.compraJogo(jogoVendido);
	}
	
	public void recompensar(String nomeJogo,int scoreObtido,boolean zerou, String login) throws ValorInvalidoException, StringInvalidaException{
		Usuario usr = this.buscaUsuario(login);
		usr.recompensar(nomeJogo, scoreObtido, zerou);
	}
	
	public void punir(String nomeJogo, int scoreObtido, boolean zerou,  String login) throws ValorInvalidoException, StringInvalidaException{
		Usuario usr = this.buscaUsuario(login);
		usr.punir(nomeJogo, scoreObtido, zerou);
	}

	public void adicionaCredito(String login, double credito) throws ValorInvalidoException {
		if (credito < 0) {
			throw new ValorInvalidoException("Credito nao pode ser negativo");
		}
		Usuario user = this.buscaUsuario(login);
		user.setCredito(user.getCredito() + credito);
	}

	public Usuario buscaUsuario(String login) {
		Usuario buscado = null;
		for (int i = 0; i < meusUsuarios.size(); i++) {
			if (meusUsuarios.get(i).getLogin().equals(login)) {
				buscado = meusUsuarios.get(i);
			}
		}
		return buscado;
	}
	
	public void upgrade(String login) throws UpgradeInvalidoException, StringInvalidaException{
		Usuario usr = this.buscaUsuario(login);
		usr.upgrade();
	}
	
	public void downgrade(String login) throws UpgradeInvalidoException, StringInvalidaException{
		Usuario usr = this.buscaUsuario(login);
		usr.downgrade();
	}
	

	/*public void upgrade(String login) throws UpgradeInvalidoException, StringInvalidaException {
		Usuario antigo = this.buscaUsuario(login);
		if (antigo instanceof Veterano) {
			throw new UpgradeInvalidoException("Impossivel realizar upgrade, Usuario já é Veterano!");
		} else if (antigo.getXp2() < 1001) {
			throw new UpgradeInvalidoException("Impossivel realizar upgrade, quantidade de x2p insuficiente!");
		}
		Usuario novo = new Veterano(antigo.getNome(), antigo.getLogin());
		novo.setCredito(antigo.getCredito());
		novo.setXp2(antigo.getXp2());
		novo.setMeusJogos(antigo.getMeusJogos());
		int index = meusUsuarios.indexOf(antigo);
		meusUsuarios.set(index, novo);

	}
	
	public void downgrade(String login) throws DowngradeInvalidoException, StringInvalidaException {
		Usuario antigo = this.buscaUsuario(login);
		if (antigo instanceof Noob) {
			throw new DowngradeInvalidoException("Impossivel realizar downgrade, Usuario já é Noob!");
		} else if (antigo.getXp2() > 1000) {
			throw new DowngradeInvalidoException("Impossivel realizar downgrade, quantidade de x2p insuficiente!");
		}
		Usuario novo = new Noob(antigo.getNome(), antigo.getLogin());
		novo.setCredito(antigo.getCredito());
		novo.setXp2(antigo.getXp2());
		novo.setMeusJogos(antigo.getMeusJogos());
		int index = meusUsuarios.indexOf(antigo);
		meusUsuarios.set(index, novo);

	}*/

	public double confereCredito(String login) {
		Usuario procurado = this.buscaUsuario(login);
		return procurado.getCredito();
	}

	public String informacaoUsuarios() {
		String myString = "=== Central P2-CG ===" + FIM_DE_LINHA + FIM_DE_LINHA;
		for (int i = 0; i < meusUsuarios.size(); i++) {
			myString += meusUsuarios.get(i).toString() + FIM_DE_LINHA;
		}
		return myString;
	}

	public int getX2p(String login) {
		Usuario buscado = this.buscaUsuario(login);
		return buscado.getXp2();
	}

	// feito a parte de cria jogo com factory
	private Jogo criaJogo(String jogoNome, double preco, Set<Jogabilidade> tiposJogabilidades, String estiloJogo) 
			throws StringInvalidaException, PrecoInvalidoException {
		String estilo = estiloJogo.toLowerCase();
		return this.factoryJogo.criaJogo(jogoNome, preco, tiposJogabilidades, estilo);
	}

	private Set<Jogabilidade> createJogabilidades(String names1) { // METODO QUE RECEBE UMA STRING DE JOGABILIDADE E RETORNA UMA LISTA COM AS JOGABILIDADES DO JOGO.
		Set<Jogabilidade> jogabilidades = new HashSet<Jogabilidade>();

		String[] listofNames = names1.split(" "); // MUDEI DE VIRGULA PARA ESPAÃ‡O NA QUEBRA DA STRING DE JOGABILIDADE.

		for (int i = 0; i < listofNames.length; i++) {
			String element = listofNames[i].toUpperCase();
			if (element != null) {
				Jogabilidade tipojogabilidade = mapJogabildades.get(element);
				jogabilidades.add(tipojogabilidade);
			}
		}		
		return jogabilidades;

	}

	private void initializeMap() {
		this.mapJogabildades = new HashMap<String, Jogabilidade>();
		mapJogabildades.put("ONLINE", Jogabilidade.ONLINE);
		mapJogabildades.put("OFFLINE", Jogabilidade.OFFLINE);
		mapJogabildades.put("COMPETITIVO", Jogabilidade.COMPETITIVO);
		mapJogabildades.put("COOPERATIVO", Jogabilidade.COOPERATIVO);
		mapJogabildades.put("MULTIPLAYER", Jogabilidade.MULTIPLAYER);

	}

	public static void main(String[] args) {
		args = new String[] { "loja.LojaController", "acceptance_test/us1.txt", "acceptance_test/us2.txt",  "acceptance_test/us3.txt" };
		EasyAccept.main(args);
	}
}