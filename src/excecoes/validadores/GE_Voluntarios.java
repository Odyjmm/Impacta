package excecoes.validadores;

import java.util.HashMap;

import entidades.voluntario.Voluntario;
import excecoes.classes.RegistroDuplicadoException;

public class GE_Voluntarios extends GeradorExcecoes {
	public void validaCadastroVoluntario(String email, String nome, String matricula, boolean contains) {
		super.verificadorStringInvalida(email, "Erro ao cadastrar novo voluntário: O campo email não pode ser vazio ou nulo");
		if (contains) throw new RegistroDuplicadoException("Erro ao cadastrar novo voluntário: O campo email contém dados já previamente cadastrados");
		
		super.verificadorStringInvalida(nome, "Erro ao cadastrar novo voluntário: O campo nome não pode ser vazio ou nulo");
		
		super.verificadorStringInvalida(matricula, "Erro ao cadastrar novo voluntário: O campo matrícula não pode ser vazio ou nulo");
	}
	
	public void validaVoluntarioEhCadastrado(HashMap<String, Voluntario> map, String email) {
		super.verificadorCadastroVoluntario(map, email, "Erro ao exibir cadastro de voluntário: Esse email não está cadastrado");
	}
	
	public void validaListaVazia(HashMap<String, Voluntario> map) {
		super.vericadorListaVazia(map, "Erro ao listar voluntários: Não há voluntários cadastrados no sistema");
	}
}
