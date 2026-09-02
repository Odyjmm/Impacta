package excecoes.validadores;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;

import entidades.acoes.Acao;
import entidades.voluntario.Voluntario;

import excecoes.classes.AcaoLotadaException;
import excecoes.classes.DataInvalidaException;
import excecoes.classes.ListaVaziaException;
import excecoes.classes.ValorInvalidoException;
import excecoes.classes.RegistroDuplicadoException;
import excecoes.classes.RegistroInexistenteException;
import excecoes.classes.StringInvalidaExcepition;

public abstract class GeradorExcecoes {
	protected void verificadorStringInvalida(String s, String msg) {
		if (s == null) throw new StringInvalidaExcepition(msg);
		
		if ("".equals(s.trim().strip())) throw new StringInvalidaExcepition(msg);
	}
	
	protected void verificadorCadastroVoluntario(HashMap<String, Voluntario> map, String email, String msg) {
		if (!map.containsKey(email)) throw new RegistroInexistenteException(msg);
	}
	
	protected void vericadorListaVazia(HashMap<String, Voluntario> map, String msg) {
		if (map.isEmpty()) throw new ListaVaziaException(msg);
	}
	
	protected void verificadorDataInvalida(String data, String msg) {
		if (data == null) throw new DataInvalidaException(msg);
		
		if ("".equals(data.trim().strip())) throw new DataInvalidaException(msg);
		
		try {
			DateTimeFormatter f = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDate.parse(data, f);
		} catch (DateTimeParseException e) {
			throw new DataInvalidaException(msg);
		}
	}
	
	protected void verificadorAcaoCadastrada(HashMap<Integer, Acao> map, int idAcao, String msg) {
		if (!map.containsKey(idAcao)) throw new RegistroInexistenteException(msg);
	}
	
	protected void verificadorValorInvalido(int v, String msg) {
		if (v <= 0) throw new ValorInvalidoException(msg);
	}
	
	protected void verificadorAcaoLotada(int qtdMax, int qtdAtual, String msg) {
		if (qtdAtual >= qtdMax) throw new AcaoLotadaException(msg);
	}
	
	protected void verificadorAlunoDuplicado(ArrayList<String> lista, String email, String msg) {
		if (lista.contains(email)) throw new RegistroDuplicadoException(msg);
	}
}