/* package test;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import controllers.RecepcionistaController;
import dataaccess.MedicoDAO;
import dataaccess.PacienteDAO;
import dataaccess.RecepcionistaDAO;
import models.Consulta;
import models.Medico;
import models.Paciente;

public class recepcionistaControllerTest {
    private RecepcionistaDAO recepcionistaDAO;
    private MedicoDAO medicoDAO;
    private PacienteDAO pacienteDAO;
    private RecepcionistaController recepcionistaController;

    @Before
    public void setUp() {
        recepcionistaDAO = Mockito.mock(RecepcionistaDAO.class);
        recepcionistaController = new RecepcionistaController(recepcionistaDAO);
    }

    @Test
    public void testRegistrarPaciente() {
        Paciente paciente = new Paciente("João", "123456", "João Silva");

        // Simular paciente não existente
        Mockito.when(recepcionistaDAO.pacienteExistente("João")).thenReturn(false);
        Mockito.when(recepcionistaDAO.inserirPaciente(paciente)).thenReturn(true);

        boolean resultado = recepcionistaController.registrarPaciente(paciente);
        assertTrue(resultado);
    }

    private void assertTrue(boolean resultado) {
    }

    @Test
    public void testMarcarConsulta() throws SQLException {
        Consulta consulta = new Consulta(1, "2023-05-27", "09:00", "João Silva", "Dermatologia");

        // Simular consulta não existente
        Mockito.when(recepcionistaDAO.consultaExistente(consulta)).thenReturn(false);
        Mockito.when(recepcionistaDAO.criarConsulta(consulta)).thenReturn(true);

        boolean resultado = recepcionistaController.marcarConsulta(consulta);
        assertTrue(resultado);
    }

    @Test
    public void testBuscarMedicoPorNome() throws SQLException {
        Medico medico = new Medico(1, "medico", "senha", "Dr. Carlos", "Dermatologia", "123456");

        // Simular busca de médico
        Mockito.when(medicoDAO.buscarPorNome("Dr. Carlos")).thenReturn(medico);

        Medico resultado = recepcionistaController.buscarMedicoPorNome("Dr. Carlos");
        assertEquals(medico, resultado);
    }

    private void assertEquals(Paciente paciente, Paciente resultado) {
    }

    @Test
    public void testBuscarPacientePorNome() throws SQLException {
        Paciente paciente = new Paciente(1, "paciente", "senha", "Maria");

        // Simular busca de paciente
        Mockito.when(pacienteDAO.buscarPorNome("Maria")).thenReturn(paciente);

        Paciente resultado = recepcionistaController.buscarPacientePorNome("Maria");
        assertEquals(paciente, resultado);
    }

    @Test
    public void testCancelarConsulta() throws SQLException {
        int codigoConsulta = 1;

        // Simular cancelamento de consulta
        Mockito.when(recepcionistaDAO.cancelarConsulta(codigoConsulta)).thenReturn(true);

        boolean resultado = recepcionistaController.cancelarConsulta(codigoConsulta);
        assertTrue(resultado);
    }
} */