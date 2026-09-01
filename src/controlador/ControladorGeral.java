package controlador;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import entidades.Voluntario;
import entidades.acoes.Acao;
import entidades.acoes.Multirao;
import entidades.acoes.Oficina;
import entidades.acoes.Plantio;
import excecoes.validadores.GE_Acoes;
import excecoes.validadores.GE_Voluntarios;

public class ControladorGeral {
	private HashMap<String, Voluntario> voluntarios;
	private HashMap<Integer, Acao> acoes;
	private HashMap<Integer, ArrayList<String>> voluntariosPorAcao;
	private GE_Voluntarios gev;
	private GE_Acoes gea;
	private int idAtual;

	public ControladorGeral() {
		this.voluntarios = new HashMap<String, Voluntario>();
		this.acoes = new HashMap<Integer, Acao>();
		this.voluntariosPorAcao = new HashMap<Integer, ArrayList<String>>();
		this.gev = new GE_Voluntarios();
		this.gea = new GE_Acoes();
		this.idAtual = 0;
	}
	
	public boolean cadastrarVoluntario(String email, String nome, String matricula) {
		gev.validaCadastroVoluntario(email, nome, matricula, voluntarios.containsKey(email));
				
		return true;
	}
	
	public String exibirVoluntario(String email) {
		gev.validaVoluntarioEhCadastrado(voluntarios, email);
		
		return voluntarios.get(email).toString();
	}
	
	public String[] listarVoluntarios() {
		gev.validaListaVazia(voluntarios);
		
		List<Voluntario> listaVoluntarios = new ArrayList<Voluntario>(voluntarios.values());
		
	    listaVoluntarios.sort(
	            Comparator.comparingInt(Voluntario::getPontuacaoImpacto)
	                    .reversed()
	                    .thenComparing(Voluntario::getNome)
	        );
		
	    String[] resultado = new String[listaVoluntarios.size()];
		
	    for (int i = 0; i < listaVoluntarios.size(); i++) {
	        resultado[i] = listaVoluntarios.get(i).detalhes();
	    }
	    
		return resultado;
	}
	
	public int cadastrarPlantio(String titulo, String descricao, String data, int maxParticipantes, int qtdMudas) {
		gea.validaCadastroPlantio(titulo, descricao, data, maxParticipantes, qtdMudas);
		
		idAtual++;
		acoes.put(idAtual, new Plantio(titulo, descricao, data, maxParticipantes, qtdMudas));
		voluntariosPorAcao.put(idAtual, new ArrayList<String>());
		
		return idAtual;
	}
	
	public int cadastrarMutirao(String titulo, String descricao, String data, int maxParticipantes, int duracaoHoras) {
		gea.validaCadastroMultirao(titulo, descricao, data, maxParticipantes, duracaoHoras);
		
		idAtual++;
		acoes.put(idAtual, new Multirao(titulo, descricao, data, maxParticipantes, duracaoHoras));
		voluntariosPorAcao.put(idAtual, new ArrayList<String>());
		
		return idAtual;
	}
	
	public int cadastrarOficina(String titulo, String descricao, String data, int maxParticipantes, int duracaoHoras, boolean kitMaterial) {
		gea.validaCadastroOficina(titulo, descricao, data, maxParticipantes, duracaoHoras);
		
		idAtual++;
		acoes.put(idAtual, new Oficina(titulo, descricao, data, maxParticipantes, duracaoHoras, kitMaterial));
		voluntariosPorAcao.put(idAtual, new ArrayList<String>());
		
		return idAtual;
	}
	
	public boolean inscreverVoluntario(String emailVoluntario, int idAcao) {
		gev.validaVoluntarioEhCadastrado(voluntarios, emailVoluntario);
		gea.validaAcaoEhCadastrada(acoes, idAcao);
		
		gea.validaInscricaoVoluntario(voluntariosPorAcao.get(idAcao), emailVoluntario, acoes.get(idAtual).getCapacidaMaxima(), voluntariosPorAcao.get(idAcao).size());
		
		voluntariosPorAcao.get(idAcao).add(emailVoluntario);
		
		return true;
	}
	
	public String exibirDetalhesAcao(int idAcao) {
		gea.validaAcaoEhCadastrada(acoes, idAcao);
		
		return acoes.get(idAcao).toString();
	}
}
