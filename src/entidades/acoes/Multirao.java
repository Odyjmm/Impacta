package entidades.acoes;

public class Multirao extends Acao {
	private int duracao;

	public Multirao(String titulo, String descricao, String data, int capacidaMaxima, int duracao) {
		super(titulo, descricao, data, capacidaMaxima);
		this.duracao = duracao;
	}

	@Override
	public int calculapontuacao() {
		return 4 * duracao;
	}

	public int getDuracao() {
		return duracao;
	}

	public void setDuracao(int duracao) {
		this.duracao = duracao;
	}

	@Override
	public String toString() {
		return super.toString() + "\nDuração: " + duracao + "h";
	}

}
