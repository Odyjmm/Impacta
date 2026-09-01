package excecoes.classes;

public class MatriculaInvalidaException extends RuntimeException {
	private static final long serialVersionUID = 2788195492126648982L;

	public MatriculaInvalidaException() {
		super();
	}

	public MatriculaInvalidaException(String message) {
		super(message);
	}
}
