package excecoes.validadores;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;

import entidades.acoes.Acao;
import entidades.voluntario.Voluntario;
import excecoes.classes.AcaoLotadaException;
import excecoes.classes.DataInvalidaException;
import excecoes.classes.DescricaoInvalidaException;
import excecoes.classes.DuracaoInvalidaException;
import excecoes.classes.EmailInvalidoExcepetion;
import excecoes.classes.ListaVaziaException;
import excecoes.classes.MatriculaInvalidaException;
import excecoes.classes.NomeInvalidoException;
import excecoes.classes.QuantidadeInvalidaException;
import excecoes.classes.RegistroDuplicadoException;
import excecoes.classes.RegistroInexistenteException;
import excecoes.classes.TituloInvalidoException;

public abstract class GeradorExcecoes {
	protected void verificadorNomeInvalido(String nome, String msg) {
		if (nome == null) throw new NomeInvalidoException(msg);
		
		if ("".equals(nome.trim().strip())) throw new NomeInvalidoException(msg);
	}
	
	protected void verificadorEmailInválido(String email, String msg) {
		if (email == null) throw new EmailInvalidoExcepetion(msg);
		
		if ("".equals(email.trim().strip())) throw new EmailInvalidoExcepetion(msg);
	}
	
	protected void verificadorMatriculaInvalida(String matricula, String msg) {
		if (matricula == null) throw new MatriculaInvalidaException(msg);
		
		if ("".equals(matricula.trim().strip())) throw new MatriculaInvalidaException(msg);
	}
	
	protected void verificadorCadastroVoluntario(HashMap<String, Voluntario> map, String email, String msg) {
		if (!map.containsKey(email)) throw new RegistroInexistenteException(msg);
	}
	
	protected void vericadorListaVazia(HashMap<String, Voluntario> map, String msg) {
		if (map.isEmpty()) throw new ListaVaziaException(msg);
	}
	
	protected void verificadorTituloInvalido(String titulo, String msg) {
		if (titulo == null) throw new TituloInvalidoException(msg);
		
		if ("".equals(titulo.trim().strip())) throw new TituloInvalidoException(msg);
	}
	
	protected void verificadorDescricaoInvalida(String descricao, String msg) {
		if (descricao == null) throw new DescricaoInvalidaException(msg);
		
		if ("".equals(descricao.trim().strip())) throw new DescricaoInvalidaException(msg);
	}
	
	protected void verificadorDataInvalida(String data, String msg) {
		if (data == null) throw new DataInvalidaException(msg);
		
		if ("".equals(data.trim().strip())) throw new DataInvalidaException(msg);
		
		try {
			DateTimeFormatter f = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDateTime.parse(data, f);
		} catch (DateTimeParseException e) {
			throw new DataInvalidaException(msg);
		}
	}
	
	protected void verificadorAcaoCadastrada(HashMap<Integer, Acao> map, int idAcao, String msg) {
		if (!map.containsKey(idAcao)) throw new RegistroInexistenteException(msg);
	}
	
	protected void verificadorQuantidadeInvalida(int qtd, String msg) {
		if (qtd <= 0) throw new QuantidadeInvalidaException(msg);
	}
	
	protected void verificadorDuracaoInvalida(int duracao, String msg) {
		if (duracao <= 0) throw new DuracaoInvalidaException(msg);
	}
	
	protected void verificadorAcaoLotada(int qtdMax, int qtdAtual, String msg) {
		if (qtdAtual >= qtdMax) throw new AcaoLotadaException(msg);
	}
	
	protected void verificadorAlunoDuplicado(ArrayList<String> lista, String email, String msg) {
		if (lista.contains(email)) throw new RegistroDuplicadoException(msg);
	}
}
