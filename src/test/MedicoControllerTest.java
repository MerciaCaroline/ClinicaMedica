package test;

import dataaccess.HistoricoPacienteDAO;
import dataaccess.MedicoDAO;
import models.Exame;
import models.HistoricoPaciente;
import models.Medico;
import models.Paciente;
import org.junit.Before;
import org.junit.Test;

import controllers.ExameController;
import controllers.MedicoController;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class MedicoControllerTest {
    private MedicoController medicoController;
    private MedicoDAO medicoDAOMock;
    private HistoricoPacienteDAO historicoPacienteDAOMock;
    private ExameController exameControllerMock;

    @Before
    public void setUp() {
        Connection connectionMock = mock(Connection.class);
        medicoDAOMock = mock(MedicoDAO.class);
        historicoPacienteDAOMock = mock(HistoricoPacienteDAO.class);
        exameControllerMock = mock(ExameController.class);

        medicoController = new MedicoController(connectionMock);
        medicoController.setMedicoDAO(medicoDAOMock);
        medicoController.setHistoricoDAO(historicoPacienteDAOMock);
        medicoController.exameController = exameControllerMock;
    }

    @Test
    public void testRegistrarHistoricoPaciente_PacienteNotNull() throws SQLException {
        // Mocking
        Paciente paciente = mock(Paciente.class);
        String historicoPaciente = "Histórico do Paciente";
        HistoricoPaciente historico = new HistoricoPaciente(paciente, historicoPaciente);
        historicoPacienteDAOMock.adicionarHistoricoPaciente(historico);

        // Test
        boolean result = medicoController.registrarHistoricoPaciente(paciente, historicoPaciente);

        // Verification
        verify(historicoPacienteDAOMock).adicionarHistoricoPaciente(historico);
        assertTrue(result);
    }

    @Test
    public void testRegistrarHistoricoPaciente_PacienteNull() throws SQLException {
        // Mocking
        Paciente paciente = null;
        String historicoPaciente = "Histórico do Paciente";

        // Test
        boolean result = medicoController.registrarHistoricoPaciente(paciente, historicoPaciente);

        // Verification
        assertFalse(result);
    }

    @Test
    public void testConsultarResultadosExames() throws SQLException {
        // Mocking
        Medico medico = mock(Medico.class);
        Paciente paciente = mock(Paciente.class);
        Exame exame1 = mock(Exame.class);
        Exame exame2 = mock(Exame.class);
        List<Exame> exames = new ArrayList<>();
        exames.add(exame1);
        exames.add(exame2);

        when(exameControllerMock.buscarTodosExames()).thenReturn(exames);
        when(exame1.getResultado()).thenReturn("Resultado 1");
        when(exame2.getResultado()).thenReturn(null);
        when(exame1.getMedico()).thenReturn(medico);
        when(exame2.getMedico()).thenReturn(medico);
        when(exame1.getPaciente()).thenReturn(paciente);
        when(exame2.getPaciente()).thenReturn(paciente);
        when(medico.getId()).thenReturn(1);
        when(paciente.getId()).thenReturn(1);

        // Test
        List<Exame> result = medicoController.consultarResultadosExames(medico, paciente);

        // Verification
        verify(exameControllerMock).buscarTodosExames();
        assertEquals(1, result.size());
        assertEquals(exame1, result.get(0));
    }

    @Test
    public void testBuscarMedicoPorNome() throws SQLException {
        // Mocking
        String nome = "Dr. John Doe";
        Medico medico = mock(Medico.class);

        medicoController.setMedicoDAO(mock(MedicoDAO.class));
        when(medicoController.getMedicoDAO().buscarPorNome(nome)).thenReturn(medico);

        // Test
        Medico result = medicoController.buscarMedicoPorNome(nome);

        // Verification
        verify(medicoController.getMedicoDAO()).buscarPorNome(nome);
        assertEquals(medico, result);
    }

    @Test
    public void testBuscarMedicoPorId() throws SQLException {
        // Mocking
        int id = 1;
        Medico medico = mock(Medico.class);

        medicoController.setMedicoDAO(mock(MedicoDAO.class));
        when(medicoController.getMedicoDAO().buscarPorId(id)).thenReturn(medico);

        // Test
        Medico result = medicoController.buscarMedicoPorId(id);

        // Verification
        verify(medicoController.getMedicoDAO()).buscarPorId(id);
        assertEquals(medico, result);
    }

    @Test
    public void testBuscarMedicoPorUsuario() throws SQLException {
        // Mocking
        String usuario = "johndoe";
        Medico medico = mock(Medico.class);

        medicoController.setMedicoDAO(mock(MedicoDAO.class));
        when(medicoController.getMedicoDAO().buscarPorUsuario(usuario)).thenReturn(medico);

        // Test
        Medico result = medicoController.buscarMedicoPorUsuario(usuario);

        // Verification
        verify(medicoController.getMedicoDAO()).buscarPorUsuario(usuario);
        assertEquals(medico, result);
    }

    @Test
    public void testAutorizarExameOnline_ExameNotFound() throws SQLException {
        // Mocking
        int codigoExame = 123;
        medicoController.exameController = mock(ExameController.class);
        when(medicoController.exameController.buscarExame(codigoExame)).thenReturn(null);

        // Test
        String resultado = medicoController.autorizarExameOnline();

        // Verification
        assertEquals(resultado, "Exame não encontrado.");
        verify(medicoController.exameController).buscarExame(codigoExame);
    }

    @Test
    public void testAutorizarExameOnline_ExameNotAuthorized() throws SQLException {
        // Mocking
        int codigoExame = 123;
        Exame exame = mock(Exame.class);
        Medico medico = mock(Medico.class);

        medicoController.exameController = mock(ExameController.class);
        when(medicoController.exameController.buscarExame(codigoExame)).thenReturn(exame);
        when(exame.getMedico()).thenReturn(medico);
        when(medico.getId()).thenReturn(2);

        // Test
        String resultado = medicoController.autorizarExameOnline();

        // Verification
        assertEquals(resultado, "Não foi possível disponibilizar o exame");
        verify(medicoController.exameController).buscarExame(codigoExame);
        assertEquals(1, verify(exameControllerMock, times(0)).disponibilizarExameOnline(codigoExame));
    }

    @Test
    public void testAutorizarExameOnline_ExameAuthorized() throws SQLException {
        // Mocking
        int codigoExame = 123;
        Exame exame = mock(Exame.class);
        Medico medico = mock(Medico.class);

        medicoController.exameController = mock(ExameController.class);

        when(medicoController.exameController.buscarExame(codigoExame)).thenReturn(exame);
        when(exame.getMedico()).thenReturn(medico);
        when(medico.getId()).thenReturn(1);
        when(exame.isDisponivelOnline()).thenReturn(true);

        // Test
        String resultado = medicoController.autorizarExameOnline();

        // Verification
        assertEquals(resultado, "Exame autorizado para disponibilidade.");
        verify(medicoController.exameController).buscarExame(codigoExame);
        assertEquals(1, verify(exameControllerMock).disponibilizarExameOnline(codigoExame));
    }
}
