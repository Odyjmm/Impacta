package excecoes.classes;

public class DescricaoInvalidaException extends RuntimeException {
	private static final long serialVersionUID = -1170987679129849845L;

	public DescricaoInvalidaException() {
		super();
	}

	public DescricaoInvalidaException(String message) {
		super(message);
	}
}
