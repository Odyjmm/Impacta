package entidades.acoes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public abstract class Acao {
	private String titulo;
	private String descricao;
	private LocalDateTime data;
	private int capacidaMaxima;
	
	public Acao(String titulo, String descricao, String data, int capacidaMaxima) {
		this.titulo = titulo;
		this.descricao = descricao;
		this.capacidaMaxima = capacidaMaxima;
		
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		this.data = LocalDateTime.parse(data, formato);
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	public int getCapacidaMaxima() {
		return capacidaMaxima;
	}

	public void setCapacidaMaxima(int capacidaMaxima) {
		this.capacidaMaxima = capacidaMaxima;
	}
	
	public abstract int calculapontuacao();

	@Override
	public int hashCode() {
		return Objects.hash(Integer.valueOf(capacidaMaxima), data, descricao, titulo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Acao other = (Acao) obj;
		return capacidaMaxima == other.capacidaMaxima && Objects.equals(data, other.data)
				&& Objects.equals(descricao, other.descricao) && Objects.equals(titulo, other.titulo);
	}

	@Override
	public String toString() {
	    return "Título: " + titulo +
	           "\nDescrição: " + descricao +
	           "\nData: " + data;
	}
}
