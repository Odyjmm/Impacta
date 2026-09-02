package excecoes.classes;

public class ValorInvalidoException extends RuntimeException {
	private static final long serialVersionUID = 1969438667664847840L;

	public ValorInvalidoException() {
		super();
	}

	public ValorInvalidoException(String message) {
		super(message);
	}
}
