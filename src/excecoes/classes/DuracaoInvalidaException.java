package excecoes.classes;

public class DuracaoInvalidaException extends RuntimeException {
	private static final long serialVersionUID = 6653624900396667349L;

	public DuracaoInvalidaException() {
		super();
	}

	public DuracaoInvalidaException(String message) {
		super(message);
	}
}
