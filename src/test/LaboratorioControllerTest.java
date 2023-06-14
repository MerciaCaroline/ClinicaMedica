package test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import controllers.LaboratorioController;
import dataaccess.LaboratorioDAO;
import models.Laboratorio;

public class LaboratorioControllerTest {

    private LaboratorioController laboratorioController;

    @Mock
    private LaboratorioDAO laboratorioDAOMock;

    @Mock
    private Connection connectionMock;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        laboratorioController = new LaboratorioController(connectionMock);
        laboratorioController.setLaboratorioDAO(laboratorioDAOMock);
    }

    @Test
    public void testBuscarPorId() throws SQLException {
        int id = 1;
        Laboratorio laboratorio = new Laboratorio();
        when(laboratorioDAOMock.buscarPorId(id)).thenReturn(laboratorio);

        Laboratorio result = laboratorioController.buscarPorId(id);

        assertEquals(laboratorio, result);
        verify(laboratorioDAOMock).buscarPorId(id);
    }

    @Test
    public void testBuscarPorUsuario() throws SQLException {
        String usuario = "lab123";
        Laboratorio laboratorio = new Laboratorio();
        when(laboratorioDAOMock.buscarPorUsuario(usuario)).thenReturn(laboratorio);

        Laboratorio result = laboratorioController.buscarPorUsuario(usuario);

        assertEquals(laboratorio, result);
        verify(laboratorioDAOMock).buscarPorUsuario(usuario);
    }

    @Test
    public void testBuscarTodos() throws SQLException {
        List<Laboratorio> laboratorios = new ArrayList<>();
        laboratorios.add(new Laboratorio());
        laboratorios.add(new Laboratorio());

        when(laboratorioDAOMock.buscarTodos()).thenReturn(laboratorios);

        List<Laboratorio> result = laboratorioController.buscarTodos();

        assertEquals(laboratorios, result);
        verify(laboratorioDAOMock).buscarTodos();
    }

}
