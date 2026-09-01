package excecoes.classes;

public class DataInvalidaException extends RuntimeException {
	private static final long serialVersionUID = -3918210396028067654L;

	public DataInvalidaException() {
		super();
	}

	public DataInvalidaException(String message) {
		super(message);
	}
}
