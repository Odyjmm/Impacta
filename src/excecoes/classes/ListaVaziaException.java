package excecoes.classes;

public class ListaVaziaException extends RuntimeException {
	private static final long serialVersionUID = -4448137686358021769L;

	public ListaVaziaException() {
		super();
	}

	public ListaVaziaException(String message) {
		super(message);
	}
}