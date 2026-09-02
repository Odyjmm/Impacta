package testes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import excecoes.classes.AcaoLotadaException;
import excecoes.classes.DataInvalidaException;
import excecoes.classes.ListaVaziaException;
import excecoes.classes.RegistroDuplicadoException;
import excecoes.classes.RegistroInexistenteException;
import excecoes.classes.StringInvalidaExcepition;
import excecoes.classes.ValorInvalidoException;

import controlador.ControladorGeral;

public class ControladorGeralTeste {
	private ControladorGeral controlador;
	
    private static final String EMAIL_1 = "joao@email.com";
    private static final String NOME_1 = "João Silva";
    private static final String MATRICULA_1 = "2021001";

    private static final String EMAIL_2 = "maria@email.com";
    private static final String NOME_2 = "Maria Souza";
    private static final String MATRICULA_2 = "2021002";

    private static final String DATA_VALIDA = "10/09/2026";
    
    private static final String VAZIO = "";
    private static final String VAZIO_COM_ESPACOS = "   ";
	
	@BeforeEach
	public void setUp() {
		controlador = new ControladorGeral();
	}
	
    @Nested
    @DisplayName("cadastrarVoluntario")
    class CadastrarVoluntario {

        @Test
        @DisplayName("Deve cadastrar voluntário com dados válidos e retornar true")
        void deveCadastrarComSucesso() {
            boolean resultado = controlador.cadastrarVoluntario(EMAIL_1, NOME_1, MATRICULA_1);

            assertTrue(resultado);
            assertEquals(1, controlador.getVoluntarios().size());
            assertTrue(controlador.getVoluntarios().containsKey(EMAIL_1));
        }

        @Test
        @DisplayName("Deve retornar false ao tentar cadastrar email já existente")
        void naoDeveCadastrarEmailDuplicado() {
            controlador.cadastrarVoluntario(EMAIL_1, NOME_1, MATRICULA_1);

            boolean resultado = controlador.cadastrarVoluntario(EMAIL_1, "Outro Nome", "9999999");

            assertFalse(resultado);
            assertEquals(1, controlador.getVoluntarios().size());
        }

        @Test
        @DisplayName("Deve retornar false quando email é nulo ou vazio")
        void naoDeveCadastrarComEmailInvalido() {
            assertFalse(controlador.cadastrarVoluntario(null, NOME_1, MATRICULA_1));
            assertFalse(controlador.cadastrarVoluntario(VAZIO, NOME_1, MATRICULA_1));
            assertFalse(controlador.cadastrarVoluntario(VAZIO_COM_ESPACOS, NOME_1, MATRICULA_1));
        }

        @Test
        @DisplayName("Deve retornar false quando nome é nulo ou vazio")
        void naoDeveCadastrarComNomeInvalido() {
            assertFalse(controlador.cadastrarVoluntario(EMAIL_1, null, MATRICULA_1));
            assertFalse(controlador.cadastrarVoluntario(EMAIL_1, VAZIO, MATRICULA_1));
            assertFalse(controlador.cadastrarVoluntario(EMAIL_1, VAZIO_COM_ESPACOS, MATRICULA_1));
        }

        @Test
        @DisplayName("Deve retornar false quando matrícula é nula ou vazia")
        void naoDeveCadastrarComMatriculaInvalida() {
            assertFalse(controlador.cadastrarVoluntario(EMAIL_1, NOME_1, null));
            assertFalse(controlador.cadastrarVoluntario(EMAIL_1, NOME_1, VAZIO));
            assertFalse(controlador.cadastrarVoluntario(EMAIL_1, NOME_1, VAZIO_COM_ESPACOS));
        }
    }
    
    @Nested
    @DisplayName("exibirVoluntario")
    class ExibirVoluntario {

        @Test
        @DisplayName("Deve retornar os detalhes (toString) de um voluntário cadastrado")
        void deveExibirVoluntarioCadastrado() {
            controlador.cadastrarVoluntario(EMAIL_1, NOME_1, MATRICULA_1);

            String detalhes = controlador.exibirVoluntario(EMAIL_1);

            assertNotNull(detalhes);
            assertTrue(detalhes.contains(NOME_1));
            assertTrue(detalhes.contains(EMAIL_1));
            assertTrue(detalhes.contains(MATRICULA_1));
        }

        @Test
        @DisplayName("Deve lançar RegistroInexistenteException para email não cadastrado")
        void deveLancarExcecaoParaVoluntarioNaoCadastrado() {
            assertThrows(RegistroInexistenteException.class,
                    () -> controlador.exibirVoluntario("naoexiste@email.com"));
        }
    }
    
    @Nested
    @DisplayName("listarVoluntarios")
    class ListarVoluntarios {

        @Test
        @DisplayName("Deve lançar ListaVaziaException quando não há voluntários")
        void deveLancarExcecaoQuandoListaVazia() {
            assertThrows(ListaVaziaException.class, () -> controlador.listarVoluntarios());
        }

        @Test
        @DisplayName("Deve ordenar voluntários por pontuação decrescente e depois por nome")
        void deveOrdenarPorPontuacaoENome() {
            controlador.cadastrarVoluntario(EMAIL_1, "Bruno", MATRICULA_1);
            controlador.cadastrarVoluntario(EMAIL_2, "Ana", MATRICULA_2);
            controlador.cadastrarVoluntario("carla@email.com", "Carla", "2021003");

            // Ana e Bruno tem a mesma pontuação (0) -> ordem alfabética entre eles
            // Carla com pontuação maior -> deve vir primeiro
            controlador.getVoluntarios().get("carla@email.com").setPontuacaoImpacto(50);

            String[] listagem = controlador.listarVoluntarios();

            assertEquals(3, listagem.length);
            assertTrue(listagem[0].contains("Carla"));
            assertTrue(listagem[1].contains("Ana"));
            assertTrue(listagem[2].contains("Bruno"));
        }
    }
    
    @Nested
    @DisplayName("cadastrarPlantio")
    class CadastrarPlantio {

        @Test
        @DisplayName("Deve cadastrar plantio válido retornando id incremental a partir de 1")
        void deveCadastrarComSucesso() {
            int id = controlador.cadastrarPlantio("Plantio Praça", "Descrição", DATA_VALIDA, 10, 20);

            assertEquals(1, id);
            assertTrue(controlador.getAcoes().containsKey(1));
            assertTrue(controlador.getVoluntariosPorAcao().get(1).isEmpty());
        }

        @Test
        @DisplayName("Deve lançar StringInvalidaExcepition para título vazio")
        void deveLancarExcecaoTituloInvalido() {
            assertThrows(StringInvalidaExcepition.class,
                    () -> controlador.cadastrarPlantio("", "Descrição", DATA_VALIDA, 10, 20));
        }

        @Test
        @DisplayName("Deve lançar StringInvalidaExcepition para descrição vazia")
        void deveLancarExcecaoDescricaoInvalida() {
            assertThrows(StringInvalidaExcepition.class,
                    () -> controlador.cadastrarPlantio("Título", null, DATA_VALIDA, 10, 20));
        }

        @Test
        @DisplayName("Deve lançar DataInvalidaException para data fora do padrão")
        void deveLancarExcecaoDataInvalida() {
            assertThrows(DataInvalidaException.class,
                    () -> controlador.cadastrarPlantio("Título", "Descrição", "2026-12-10", 10, 20));
        }

        @Test
        @DisplayName("Deve lançar ValorInvalidoException para maxParticipantes <= 0")
        void deveLancarExcecaoMaxParticipantesInvalido() {
            assertThrows(ValorInvalidoException.class,
                    () -> controlador.cadastrarPlantio("Título", "Descrição", DATA_VALIDA, 0, 20));
        }

        @Test
        @DisplayName("Deve lançar ValorInvalidoException para quantidade de mudas <= 0")
        void deveLancarExcecaoQtdMudasInvalida() {
            assertThrows(ValorInvalidoException.class,
                    () -> controlador.cadastrarPlantio("Título", "Descrição", DATA_VALIDA, 10, 0));
        }
    }
    
    @Nested
    @DisplayName("cadastrarMutirao")
    class CadastrarMutirao {

        @Test
        @DisplayName("Deve cadastrar mutirão válido")
        void deveCadastrarComSucesso() {
            int id = controlador.cadastrarMutirao("Mutirão Rio", "Descrição", DATA_VALIDA, 15, 4);

            assertEquals(1, id);
            assertTrue(controlador.getAcoes().containsKey(1));
        }

        @Test
        @DisplayName("Deve lançar ValorInvalidoException para duração <= 0")
        void deveLancarExcecaoDuracaoInvalida() {
            assertThrows(ValorInvalidoException.class,
                    () -> controlador.cadastrarMutirao("Título", "Descrição", DATA_VALIDA, 15, 0));
        }
    }
    
    @Nested
    @DisplayName("cadastrarOficina")
    class CadastrarOficina {

        @Test
        @DisplayName("Deve cadastrar oficina válida")
        void deveCadastrarComSucesso() {
            int id = controlador.cadastrarOficina("Oficina Compostagem", "Descrição", DATA_VALIDA, 8, 3, true);

            assertEquals(1, id);
            assertTrue(controlador.getAcoes().containsKey(1));
        }

        @Test
        @DisplayName("Deve lançar ValorInvalidoException para duração <= 0")
        void deveLancarExcecaoDuracaoInvalida() {
            assertThrows(ValorInvalidoException.class,
                    () -> controlador.cadastrarOficina("Título", "Descrição", DATA_VALIDA, 8, 0, false));
        }
    }
    
    @Test
    @DisplayName("Ids devem ser sequenciais e compartilhados entre os diferentes tipos de ação")
    void idsDevemSerSequenciaisEntreTipos() {
        int idPlantio = controlador.cadastrarPlantio("Plantio", "Desc", DATA_VALIDA, 10, 5);
        int idMutirao = controlador.cadastrarMutirao("Mutirão", "Desc", DATA_VALIDA, 10, 2);
        int idOficina = controlador.cadastrarOficina("Oficina", "Desc", DATA_VALIDA, 10, 2, false);

        assertEquals(1, idPlantio);
        assertEquals(2, idMutirao);
        assertEquals(3, idOficina);
    }
    
    @Nested
    @DisplayName("inscreverVoluntario")
    class InscreverVoluntario {

        private int idAcao;

        @BeforeEach
        void cadastrarBase() {
            controlador.cadastrarVoluntario(EMAIL_1, NOME_1, MATRICULA_1);
            idAcao = controlador.cadastrarPlantio("Plantio", "Desc", DATA_VALIDA, 2, 5);
        }

        @Test
        @DisplayName("Deve inscrever voluntário cadastrado em ação cadastrada com vaga")
        void deveInscreverComSucesso() {
            boolean resultado = controlador.inscreverVoluntario(EMAIL_1, idAcao);

            assertTrue(resultado);
            assertTrue(controlador.getVoluntariosPorAcao().get(idAcao).contains(EMAIL_1));
        }

        @Test
        @DisplayName("Deve lançar RegistroInexistenteException para voluntário não cadastrado")
        void deveLancarExcecaoVoluntarioNaoCadastrado() {
            assertThrows(RegistroInexistenteException.class,
                    () -> controlador.inscreverVoluntario("naoexiste@email.com", idAcao));
        }

        @Test
        @DisplayName("Deve lançar RegistroInexistenteException para ação não cadastrada")
        void deveLancarExcecaoAcaoNaoCadastrada() {
            assertThrows(RegistroInexistenteException.class,
                    () -> controlador.inscreverVoluntario(EMAIL_1, 999));
        }

        @Test
        @DisplayName("Deve lançar RegistroDuplicadoException ao inscrever o mesmo voluntário duas vezes")
        void deveLancarExcecaoInscricaoDuplicada() {
            controlador.inscreverVoluntario(EMAIL_1, idAcao);

            assertThrows(RegistroDuplicadoException.class,
                    () -> controlador.inscreverVoluntario(EMAIL_1, idAcao));
        }

        @Test
        @DisplayName("Deve lançar AcaoLotadaException quando a ação atingir a capacidade máxima")
        void deveLancarExcecaoAcaoLotada() {
            controlador.cadastrarVoluntario(EMAIL_2, NOME_2, MATRICULA_2);
            controlador.cadastrarVoluntario("carla@email.com", "Carla", "2021003");

            // idAcao tem capacidade máxima 2
            controlador.inscreverVoluntario(EMAIL_1, idAcao);
            controlador.inscreverVoluntario(EMAIL_2, idAcao);

            assertThrows(AcaoLotadaException.class,
                    () -> controlador.inscreverVoluntario("carla@email.com", idAcao));
        }

        @Test
        @DisplayName("Deve considerar a capacidade máxima da ação correta (idAcao), mesmo havendo outra ação cadastrada depois")
        void deveConsiderarCapacidadeDaAcaoCorreta() {
            // idAcao (a primeira ação) tem capacidade 2.
            // Cadastramos uma segunda ação com capacidade maior, para garantir
            // que a validação de lotação não usa por engano os dados dela.
            int idOutraAcao = controlador.cadastrarMutirao("Outro Mutirão", "Desc", DATA_VALIDA, 100, 3);

            controlador.cadastrarVoluntario(EMAIL_2, NOME_2, MATRICULA_2);
            controlador.cadastrarVoluntario("carla@email.com", "Carla", "2021003");

            controlador.inscreverVoluntario(EMAIL_1, idAcao);
            controlador.inscreverVoluntario(EMAIL_2, idAcao);

            // idAcao já está com 2/2 (capacidade máxima 2), deve lançar AcaoLotadaException
            assertThrows(AcaoLotadaException.class,
                    () -> controlador.inscreverVoluntario("carla@email.com", idAcao));

            // Já a segunda ação (idOutraAcao), com capacidade 100, ainda tem vaga
            assertDoesNotThrow(() -> controlador.inscreverVoluntario("carla@email.com", idOutraAcao));
        }
    }
    
    @Nested
    @DisplayName("exibirDetalhesAcao")
    class ExibirDetalhesAcao {

        @Test
        @DisplayName("Deve retornar o toString de uma ação cadastrada")
        void deveExibirDetalhesDeAcaoCadastrada() {
            int id = controlador.cadastrarPlantio("Plantio Central", "Descrição do plantio", DATA_VALIDA, 10, 30);

            String detalhes = controlador.exibirDetalhesAcao(id);

            assertNotNull(detalhes);
            assertTrue(detalhes.contains("Plantio Central"));
            assertTrue(detalhes.contains("Descrição do plantio"));
        }

        @Test
        @DisplayName("Deve lançar RegistroInexistenteException para ação não cadastrada")
        void deveLancarExcecaoAcaoNaoCadastrada() {
            assertThrows(RegistroInexistenteException.class,
                    () -> controlador.exibirDetalhesAcao(123));
        }
    }
}
