package excecoes.classes;

public class QuantidadeInvalidaException extends RuntimeException {
	private static final long serialVersionUID = 1969438667664847840L;

	public QuantidadeInvalidaException() {
		super();
	}

	public QuantidadeInvalidaException(String message) {
		super(message);
	}
}
