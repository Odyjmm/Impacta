package testes;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import controlador.ControladorGeral;

public class ControladorGeralTeste {
	private ControladorGeral cg;
	
	@BeforeEach
	public void setUp() {
		cg = new ControladorGeral();
	}

	@Test
	@DisplayName("Verifica se um voluntário pode ser adicionado usando informações corretas")
	public void deveCadastrarNovoVoluntario() {
		assertTrue(cg.cadastrarVoluntario("email@teste.com", "Teste", "123456"));
		assertEquals(1, cg.getVoluntarios().size());
	}
	
	@Test
	@DisplayName("Checa todos os casos em que o sistema não deve registrar um novo voluntário")
	public void naoDeveCadastrarNovoVoluntario() {
		boolean resultado;
		
		resultado = cg.cadastrarVoluntario(null, "Teste", "123456");
		assertFalse(resultado);
		assertTrue(cg.getVoluntarios().isEmpty());

		resultado = cg.cadastrarVoluntario("", "Teste", "123456");
		assertFalse(resultado);
		assertTrue(cg.getVoluntarios().isEmpty());

		resultado = cg.cadastrarVoluntario("          ", "Teste", "123456");
		assertFalse(resultado);
		assertTrue(cg.getVoluntarios().isEmpty());
		
		resultado = cg.cadastrarVoluntario("email@teste.com", null, "123456");
		assertFalse(resultado);
		assertTrue(cg.getVoluntarios().isEmpty());
		
		resultado = cg.cadastrarVoluntario("email@teste.com", "", "123456");
		assertFalse(resultado);
		assertTrue(cg.getVoluntarios().isEmpty());

		resultado = cg.cadastrarVoluntario("email@teste.com", "        ", "123456");
		assertFalse(resultado);
		assertTrue(cg.getVoluntarios().isEmpty());
				
		resultado = cg.cadastrarVoluntario("email@teste.com", "Teste", null);
		assertFalse(resultado);
		assertTrue(cg.getVoluntarios().isEmpty());
				
		resultado = cg.cadastrarVoluntario("email@teste.com", "Teste", "");
		assertFalse(resultado);
		assertTrue(cg.getVoluntarios().isEmpty());
				
		resultado = cg.cadastrarVoluntario("email@teste.com", "Teste", "      ");
		assertFalse(resultado);
		assertTrue(cg.getVoluntarios().isEmpty());
		
		resultado = cg.cadastrarVoluntario("email@teste.com", "Teste", "123456");
		assertTrue(resultado);
		assertEquals(1, cg.getVoluntarios().size());
		
		resultado = cg.cadastrarVoluntario("email@teste.com", "Teste", "123456");
		assertFalse(resultado);
		assertEquals(1, cg.getVoluntarios().size());
	}
}
