package entidades.acoes;

public class Plantio extends Acao {
	private int quantidadeMudas;
	
	public Plantio(String titulo, String descricao, String data, int capacidaMaxima, int quantidadeMudas) {
		super(titulo, descricao, data, capacidaMaxima);
		this.quantidadeMudas = quantidadeMudas;
	}

	@Override
	public int calculapontuacao() {
		return 5 + (quantidadeMudas * 2);
	}

	public int getQuantidadeMudas() {
		return quantidadeMudas;
	}

	public void setQuantidadeMudas(int quantidadeMudas) {
		this.quantidadeMudas = quantidadeMudas;
	}

	@Override
	public String toString() {
		return super.toString() + "\nQuantidade de Mudas Plantadas: " + quantidadeMudas;
	}
}
