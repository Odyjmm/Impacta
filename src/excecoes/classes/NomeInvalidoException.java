package excecoes.classes;

public class NomeInvalidoException extends RuntimeException {
	private static final long serialVersionUID = 5056681263193353456L;

	public NomeInvalidoException() {
		super();
	}

	public NomeInvalidoException(String message) {
		super(message);
	}
}
