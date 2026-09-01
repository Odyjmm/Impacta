package entidades.acoes;

public class Oficina extends Acao {
	private int duracao;
	private boolean kit;
	
	public Oficina(String titulo, String descricao, String data, int capacidaMaxima, int duracao, boolean kit) {
		super(titulo, descricao, data, capacidaMaxima);
		this.duracao = duracao;
		this.kit = kit;
	}

	@Override
	public int calculapontuacao() {
		return kit ? (3 * duracao) + 10 : 3 * duracao;
	}

	public int getDuracao() {
		return duracao;
	}

	public void setDuracao(int duracao) {
		this.duracao = duracao;
	}

	public boolean possuiKit() {
		return kit;
	}

	public void setKit(boolean kit) {
		this.kit = kit;
	}

	@Override
	public String toString() {
		return super.toString() + "\nDuracao: " + duracao + "h" + 
				String.format("Possui Kit de Materiais? %s", kit ? "SIM" : "NÃO");
	}
}
