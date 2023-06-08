package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import controllers.ExameController;
import controllers.MedicoController;
import models.Exame;
import models.Paciente;

public class MedicoControllerTest {
    private MedicoController medicoController;

    @Before
    public void setup() {
        // Configurar objetos de controle antes de cada teste
        medicoController = new MedicoController();
    }

    @Test
    public void testAutorizarExame() throws SQLException {
        // Preparar dados de teste
        ExameController exameController = medicoController.exameController;
        Exame exame = new Exame();
        exame.setId(1);
        exame.setAutorizado(false);
        exameController.cadastrarExame(exame);

        // Executar o método a ser testado
        boolean resultado = medicoController.autorizarExame(1);

        // Verificar o resultado
        assertTrue(resultado);
        assertTrue(exame.getAutorizado());
    }

    @Test
    public void testAutorizarExameCodigoInvalido() throws SQLException {
        // Executar o método a ser testado com um código inválido
        boolean resultado = medicoController.autorizarExame(999);

        // Verificar o resultado
        assertFalse(resultado);
    }

    @Test
    public void testRegistrarHistoricoPaciente() throws SQLException {
        // Preparar dados de teste
        Paciente paciente = new Paciente();
        paciente.setNome("João");
        paciente.setHistoricoPaciente("");

        // Executar o método a ser testado
        boolean resultado = medicoController.registrarHistoricoPaciente("João", "Histórico do paciente");

        // Verificar o resultado
        assertTrue(resultado);
        assertEquals("Histórico do paciente", paciente.getHistoricoPaciente());
    }

    @Test
    public void testRegistrarHistoricoPacienteNomeInvalido() throws SQLException {
        // Executar o método a ser testado com um nome inválido
        boolean resultado = medicoController.registrarHistoricoPaciente("Maria", "Histórico do paciente");

        // Verificar o resultado
        assertFalse(resultado);
    }

    @Test
    public void testConsultarResultadosExames() throws SQLException {
        // Preparar dados de teste
        ExameController exameController = medicoController.exameController;
        int medicoId = 1;
        List<Exame> resultadosEsperados = new ArrayList<>();
        resultadosEsperados.add(new Exame());
        resultadosEsperados.add(new Exame());
        exameController.atualizarExame(new Exame());
        exameController.atualizarExame(new Exame());

        // Executar o método a ser testado
        List<Exame> resultados = medicoController.consultarResultadosExames(medicoId);

        // Verificar o resultado
        assertEquals(resultadosEsperados, resultados);
    }
}
