package excecoes.validadores;

import java.util.ArrayList;
import java.util.HashMap;

import entidades.acoes.Acao;

public class GE_Acoes extends GeradorExcecoes {
	private void validaCadastro(String titulo, String descricao, String data, int maxParticipantes) {
		super.verificadorStringInvalida(titulo, "Erro ao cadastrar nova ação: O campo titulo não pode ser vazio ou nulo");
		super.verificadorStringInvalida(descricao, "Erro ao cadastrar nova ação: O campo descrição não pode ser vazio ou nulo");
		super.verificadorDataInvalida(data, "Erro ao cadastrar nova ação: O campo data está preenchido de fora do padrão dd/MM/yyyy");
		super.verificadorValorInvalido(maxParticipantes, "Erro ao cadastrar nova ação: O campo quantidade máxima de participantes não pode ser menor ou igual a zero");
	}
	
	public void validaCadastroPlantio(String titulo, String descricao, String data, int maxParticipantes, int qtdMudas) {
		this.validaCadastro(titulo, descricao, data, maxParticipantes);
		super.verificadorValorInvalido(qtdMudas, "Erro ao cadastrar nova ação do tipo 'Plantio de Mudas': O campo quantidade de mudas não pode ser menor ou igual a zero");
	}
	
	public void validaCadastroMultirao(String titulo, String descricao, String data, int maxParticipantes, int duracaoHoras) {
		this.validaCadastro(titulo, descricao, data, maxParticipantes);
		super.verificadorValorInvalido(duracaoHoras, "Erro ao cadastrar nova ação do tipo 'Multirão de Reciclagem': O campo duração do evento não ser menor ou igual a zero");
	}
	
	public void validaCadastroOficina(String titulo, String descricao, String data, int maxParticipantes, int duracaoHoras) {
		this.validaCadastro(titulo, descricao, data, maxParticipantes);
		super.verificadorValorInvalido(duracaoHoras, "Erro ao cadastrar nova ação do tipo 'Oficina Ecológica': O campo duração do evento não ser menor ou igual a zero");
	}
	
	public void validaInscricaoVoluntario(ArrayList<String> lista, String email, int qtdMax, int qtdAtual) {
		super.verificadorAcaoLotada(qtdMax, qtdAtual, "Erro ao cadastrar aluno na ação: A ação selecionada não tem vagas restantes");
		super.verificadorAlunoDuplicado(lista, email, "Erro ao cadastrar aluno na ação: O aluno já está cadastrado na ação selecionada");
	}
	
	public void validaAcaoEhCadastrada(HashMap<Integer, Acao> map, int idAcao) {
		super.verificadorAcaoCadastrada(map, idAcao, "Erro ao inscrever aluno na ação: ação não está cadastrada");
	}
}
