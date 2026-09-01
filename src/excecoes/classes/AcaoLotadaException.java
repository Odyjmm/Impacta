package excecoes.classes;

public class AcaoLotadaException extends RuntimeException {
	private static final long serialVersionUID = 1052753899184133025L;

	public AcaoLotadaException() {
		super();
	}

	public AcaoLotadaException(String message) {
		super(message);
	}
}
