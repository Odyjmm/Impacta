package entidades.voluntario;

import java.util.Objects;

public class Voluntario {
	private String email;
	private String nome;
	private String matricula;
	private int acoes;
	private int pontuacaoImpacto;
	
	public Voluntario(String email, String nome, String matricula) {
		this.nome = nome;
		this.email = email;
		this.matricula = matricula;
		this.acoes = 0;
		this.pontuacaoImpacto = 0;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public int getAcoes() {
		return acoes;
	}

	public void setAcoes(int acoes) {
		this.acoes = acoes;
	}

	public int getPontuacaoImpacto() {
		return pontuacaoImpacto;
	}

	public void setPontuacaoImpacto(int pontuacaoImpacto) {
		this.pontuacaoImpacto = pontuacaoImpacto;
	}
	
	public String detalhes() {
	    return nome +
	            " - Ações: " + acoes+
	            " - Pontuação: " + pontuacaoImpacto;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Voluntario other = (Voluntario) obj;
		return Objects.equals(email, other.email);
	}

	@Override
	public String toString() {
	    return String.format(
	        "Voluntário%n" +
	        "Nome: %s%n" +
	        "E-mail: %s%n" +
	        "Matrícula: %s%n" +
	        "Pontuação de Impacto: %d%n" +
	        "Ações: %s",
	        nome, email, matricula, pontuacaoImpacto, acoes
	    );
	}
}
