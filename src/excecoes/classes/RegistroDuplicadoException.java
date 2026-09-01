package excecoes.classes;

public class RegistroDuplicadoException extends RuntimeException {
	private static final long serialVersionUID = 8739284754915871249L;

	public RegistroDuplicadoException() {
		super();
	}

	public RegistroDuplicadoException(String message) {
		super(message);
	}
}