package excecoes.classes;

public class RegistroInexistenteException extends RuntimeException {
	private static final long serialVersionUID = -6407355574306559053L;

	public RegistroInexistenteException() {
		super();
	}

	public RegistroInexistenteException(String message) {
		super(message);
	}
}