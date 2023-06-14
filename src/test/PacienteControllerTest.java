package test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import controllers.PacienteController;
import dataaccess.ExameDAO;
import dataaccess.PacienteDAO;
import models.Exame;
import models.Paciente;

public class PacienteControllerTest {

    private PacienteController pacienteController;

    @Mock
    private PacienteDAO pacienteDAOMock;

    @Mock
    private ExameDAO exameDAOMock;

    @Mock
    private Connection connectionMock;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        pacienteController = new PacienteController(connectionMock);
        pacienteController.setPacienteDAO(pacienteDAOMock);
        pacienteController.setExameDAO(exameDAOMock);
    }

    @Test
    public void testBuscarPaciente() throws SQLException {
        int id = 1;
        Paciente paciente = new Paciente();
        when(pacienteDAOMock.buscarPorId(id)).thenReturn(paciente);

        Paciente result = pacienteController.buscarPaciente(id);

        assertEquals(paciente, result);
        verify(pacienteDAOMock).buscarPorId(id);
    }

    @Test
    public void testBuscarTodosPacientes() throws SQLException {
        List<Paciente> pacientes = new ArrayList<>();
        pacientes.add(new Paciente());
        pacientes.add(new Paciente());

        when(pacienteDAOMock.lerTodos()).thenReturn(pacientes);

        List<Paciente> result = pacienteController.buscarTodosPacientes();

        assertEquals(pacientes, result);
        verify(pacienteDAOMock).lerTodos();
    }

    @Test
    public void testBuscarPacientePorNome() throws SQLException {
        String nome = "John Doe";
        Paciente paciente = new Paciente();
        when(pacienteDAOMock.buscarPorNome(nome)).thenReturn(paciente);

        Paciente result = pacienteController.buscarPacientePorNome(nome);

        assertEquals(paciente, result);
        verify(pacienteDAOMock).buscarPorNome(nome);
    }

    @Test
    public void testAtualizarPaciente() throws SQLException {
        Paciente paciente = new Paciente();

        pacienteController.atualizarPaciente(paciente);

        verify(pacienteDAOMock).atualizar(paciente);
    }

    @Test
    public void testRemoverPaciente() throws SQLException {
        int id = 1;

        pacienteController.removerPaciente(id);

        verify(pacienteDAOMock).excluir(id);
    }

    @Test
    public void testSolicitarExame() throws SQLException {
        int exameId = 1;
        Exame exame = new Exame();
        when(exameDAOMock.buscarPorId(exameId)).thenReturn(exame);

        Exame result = pacienteController.solicitarExame(exameId);

        assertEquals(exame, result);
        verify(exameDAOMock).buscarPorId(exameId);
    }

}
