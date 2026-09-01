package excecoes.classes;

public class EmailInvalidoExcepetion extends RuntimeException {
	private static final long serialVersionUID = -644614821401184823L;

	public EmailInvalidoExcepetion() {
		super();
	}

	public EmailInvalidoExcepetion(String message) {
		super(message);
	}
}
