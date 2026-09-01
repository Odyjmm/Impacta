package excecoes.classes;

public class TituloInvalidoException extends RuntimeException {
	private static final long serialVersionUID = 3652965444252811203L;

	public TituloInvalidoException() {
		super();
	}

	public TituloInvalidoException(String message) {
		super(message);
	}
}
