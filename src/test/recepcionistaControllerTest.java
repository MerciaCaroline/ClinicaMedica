package test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import controllers.RecepcionistaController;
import dataaccess.MedicoDAO;
import dataaccess.PacienteDAO;
import dataaccess.RecepcionistaDAO;
import models.Consulta;
import models.Medico;
import models.Paciente;
import models.Recepcionista;

public class RecepcionistaControllerTest {
    private Connection connection;
    private RecepcionistaDAO recepcionistaDAOMock;
    private MedicoDAO medicoDAOMock;
    private PacienteDAO pacienteDAOMock;
    private RecepcionistaController recepcionistaController;

    @Before
    public void setUp() {
        connection = mock(Connection.class);
        recepcionistaDAOMock = mock(RecepcionistaDAO.class);
        medicoDAOMock = mock(MedicoDAO.class);
        pacienteDAOMock = mock(PacienteDAO.class);

        recepcionistaController = new RecepcionistaController(connection);
        recepcionistaController.setRecepcionistaDAO(recepcionistaDAOMock);
        recepcionistaController.setMedicoDAO(medicoDAOMock);
        recepcionistaController.setPacienteDAO(pacienteDAOMock);
    }

    @Test
    public void testRegistrarPaciente_PacienteNaoCadastrado_RegistroSucesso() throws SQLException {
        Paciente paciente = mock(Paciente.class);

        when(recepcionistaDAOMock.pacienteExistente(paciente.getNome())).thenReturn(false);
        when(recepcionistaDAOMock.inserirPaciente(paciente)).thenReturn(true);

        assertTrue(recepcionistaController.registrarPaciente(paciente));
        verify(recepcionistaDAOMock).pacienteExistente(paciente.getNome());
        verify(recepcionistaDAOMock).inserirPaciente(paciente);
    }

    @Test
    public void testRegistrarPaciente_PacienteJaCadastrado() throws SQLException {
        Paciente paciente = mock(Paciente.class);

        when(recepcionistaDAOMock.pacienteExistente(paciente.getNome())).thenReturn(true);

        assertFalse(recepcionistaController.registrarPaciente(paciente));
        verify(recepcionistaDAOMock).pacienteExistente(paciente.getNome());
        verify(recepcionistaDAOMock, never()).inserirPaciente(paciente);
    }

    @Test
    public void testRegistrarPaciente_ErroNoBancoDeDados() throws SQLException {
        Paciente paciente = mock(Paciente.class);

        when(recepcionistaDAOMock.pacienteExistente(paciente.getNome())).thenReturn(false);
        when(recepcionistaDAOMock.inserirPaciente(paciente)).thenReturn(false);

        assertFalse(recepcionistaController.registrarPaciente(paciente));
        verify(recepcionistaDAOMock).pacienteExistente(paciente.getNome());
        verify(recepcionistaDAOMock).inserirPaciente(paciente);
    }

    @Test
    public void testMarcarConsulta_ConsultaNaoExistente_CriacaoSucesso() throws SQLException {
        Consulta consulta = mock(Consulta.class);

        when(recepcionistaDAOMock.consultaExistente(consulta)).thenReturn(false);
        when(recepcionistaDAOMock.criarConsulta(consulta)).thenReturn(true);

        assertTrue(recepcionistaController.marcarConsulta(consulta));
        verify(recepcionistaDAOMock).consultaExistente(consulta);
        verify(recepcionistaDAOMock).criarConsulta(consulta);
    }

    @Test
    public void testMarcarConsulta_ConsultaJaExistente() throws SQLException {
        Consulta consulta = mock(Consulta.class);

        when(recepcionistaDAOMock.consultaExistente(consulta)).thenReturn(true);

        assertFalse(recepcionistaController.marcarConsulta(consulta));
        verify(recepcionistaDAOMock).consultaExistente(consulta);
        verify(recepcionistaDAOMock, never()).criarConsulta(consulta);
    }

    @Test
    public void testMarcarConsulta_ErroNoBancoDeDados() throws SQLException {
        Consulta consulta = mock(Consulta.class);

        when(recepcionistaDAOMock.consultaExistente(consulta)).thenReturn(false);
        when(recepcionistaDAOMock.criarConsulta(consulta)).thenReturn(false);

        assertFalse(recepcionistaController.marcarConsulta(consulta));
        verify(recepcionistaDAOMock).consultaExistente(consulta);
        verify(recepcionistaDAOMock).criarConsulta(consulta);
    }

    @Test
    public void testBuscarMedicoPorNome_MedicoEncontrado() throws SQLException {
        Medico medico = mock(Medico.class);

        when(medicoDAOMock.buscarPorNome(medico.getNome())).thenReturn(medico);

        Medico resultado = recepcionistaController.buscarMedicoPorNome(medico.getNome());
        assertEquals(medico, resultado);
        verify(medicoDAOMock).buscarPorNome(medico.getNome());
    }

    @Test
    public void testBuscarMedicoPorNome_MedicoNaoEncontrado() throws SQLException {
        String nome = "Maria";

        when(medicoDAOMock.buscarPorNome(nome)).thenReturn(null);

        Medico resultado = recepcionistaController.buscarMedicoPorNome(nome);
        assertNull(resultado);
        verify(medicoDAOMock).buscarPorNome(nome);
    }

    @Test
    public void testBuscarPacientePorNome_PacienteEncontrado() throws SQLException {
        Paciente paciente = mock(Paciente.class);

        when(pacienteDAOMock.buscarPorNome(paciente.getNome())).thenReturn(paciente);

        Paciente resultado = recepcionistaController.buscarPacientePorNome(paciente.getNome());
        assertEquals(paciente, resultado);
        verify(pacienteDAOMock).buscarPorNome(paciente.getNome());
    }

    @Test
    public void testBuscarPacientePorNome_PacienteNaoEncontrado() throws SQLException {
        String nome = "João";

        when(pacienteDAOMock.buscarPorNome(nome)).thenReturn(null);

        Paciente resultado = recepcionistaController.buscarPacientePorNome(nome);
        assertNull(resultado);
        verify(pacienteDAOMock).buscarPorNome(nome);
    }

    @Test
    public void testCancelarConsulta_ConsultaCancelada() throws SQLException {
        int codigoConsulta = 1;

        when(recepcionistaDAOMock.cancelarConsulta(codigoConsulta)).thenReturn(true);

        assertTrue(recepcionistaController.cancelarConsulta(codigoConsulta));
        verify(recepcionistaDAOMock).cancelarConsulta(codigoConsulta);
    }

    @Test
    public void testCancelarConsulta_ConsultaInexistente() throws SQLException {
        int codigoConsulta = 1;

        when(recepcionistaDAOMock.cancelarConsulta(codigoConsulta)).thenReturn(false);

        assertFalse(recepcionistaController.cancelarConsulta(codigoConsulta));
        verify(recepcionistaDAOMock).cancelarConsulta(codigoConsulta);
    }

    @Test
    public void testBuscarPorNome() throws SQLException {
        String nome = "Maria";
        Recepcionista recepcionista = mock(Recepcionista.class);

        when(recepcionistaDAOMock.buscarPorNome(nome)).thenReturn(recepcionista);

        Recepcionista resultado = recepcionistaController.buscarPorNome(nome);
        assertEquals(recepcionista, resultado);
        verify(recepcionistaDAOMock).buscarPorNome(nome);
    }

    @Test
    public void testBuscarPorId() throws SQLException {
        int id = 1;
        Recepcionista recepcionista = mock(Recepcionista.class);

        when(recepcionistaDAOMock.buscarPorId(id)).thenReturn(recepcionista);

        Recepcionista resultado = recepcionistaController.buscarPorId(id);
        assertEquals(recepcionista, resultado);
        verify(recepcionistaDAOMock).buscarPorId(id);
    }

    @Test
    public void testBuscarPorUsuario() throws SQLException {
        String usuario = "maria123";
        Recepcionista recepcionista = mock(Recepcionista.class);

        when(recepcionistaDAOMock.buscarPorUsuario(usuario)).thenReturn(recepcionista);

        Recepcionista resultado = recepcionistaController.buscarPorUsuario(usuario);
        assertEquals(recepcionista, resultado);
        verify(recepcionistaDAOMock).buscarPorUsuario(usuario);
    }

    @Test
    public void testGetters() {
        assertEquals(recepcionistaDAOMock, recepcionistaController.getRecepcionistaDAO());
        assertEquals(medicoDAOMock, recepcionistaController.getMedicoDAO());
        assertEquals(pacienteDAOMock, recepcionistaController.getPacienteDAO());
    }

    @Test
    public void testSetters() {
        RecepcionistaDAO newRecepcionistaDAOMock = mock(RecepcionistaDAO.class);
        MedicoDAO newMedicoDAOMock = mock(MedicoDAO.class);
        PacienteDAO newPacienteDAOMock = mock(PacienteDAO.class);

        recepcionistaController.setRecepcionistaDAO(newRecepcionistaDAOMock);
        recepcionistaController.setMedicoDAO(newMedicoDAOMock);
        recepcionistaController.setPacienteDAO(newPacienteDAOMock);

        assertEquals(newRecepcionistaDAOMock, recepcionistaController.getRecepcionistaDAO());
        assertEquals(newMedicoDAOMock, recepcionistaController.getMedicoDAO());
        assertEquals(newPacienteDAOMock, recepcionistaController.getPacienteDAO());
    }
}
