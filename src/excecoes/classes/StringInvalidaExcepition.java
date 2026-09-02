package excecoes.classes;

public class StringInvalidaExcepition extends RuntimeException {
	private static final long serialVersionUID = 5056681263193353456L;

	public StringInvalidaExcepition() {
		super();
	}

	public StringInvalidaExcepition(String message) {
		super(message);
	}
}
