package test;

import dataaccess.ConsultaDAO;
import models.Consulta;
import models.Medico;
import models.Paciente;
import org.junit.Before;
import org.junit.Test;

import controllers.ConsultaController;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Date;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ConsultaControllerTest {
    private ConsultaController consultaController;
    private ConsultaDAO consultaDAOMock;

    @Before
    public void setUp() {
        Connection connectionMock = mock(Connection.class);
        consultaDAOMock = mock(ConsultaDAO.class);
        consultaController = new ConsultaController(connectionMock);
        consultaController.setConsultaDAO(consultaDAOMock);
    }

    @Test
    public void testMarcarConsulta() throws SQLException {
        Paciente paciente = mock(Paciente.class);
        Medico medico = mock(Medico.class);
        Date data = new Date(System.currentTimeMillis());
        Time hora = new Time(System.currentTimeMillis());
        String telefone = "9876543210";

        consultaController.marcarConsulta(paciente, medico, data, hora, telefone);
    }

    @Test
    public void testRemarcarConsulta_ConsultaEncontrada() throws SQLException {
        int consultaId = 1;
        Date data = new Date(System.currentTimeMillis());
        Time hora = new Time(System.currentTimeMillis());

        Consulta consulta = new Consulta();
        consulta.setId(consultaId);

        when(consultaDAOMock.buscarConsultaPorId(consulta.getId())).thenReturn(consulta);

        consultaController.remarcarConsulta(consultaId, data, hora);

        verify(consultaDAOMock).buscarConsultaPorId(consultaId);
        verify(consultaDAOMock).remarcarConsulta(consulta);
    }

    @Test
    public void testRemarcarConsulta_ConsultaNaoEncontrada() throws SQLException {
        int consultaId = 1;
        Date data = new Date(System.currentTimeMillis());
        Time hora = new Time(System.currentTimeMillis());

        Consulta consulta = new Consulta();
        consulta.setId(consultaId);

        when(consultaDAOMock.buscarConsultaPorId(consulta.getId())).thenReturn(null);

        consultaController.remarcarConsulta(consultaId, data, hora);

        verify(consultaDAOMock).buscarConsultaPorId(consultaId);
        verify(consultaDAOMock, never()).remarcarConsulta(consulta);
        // Verifica se a mensagem "Consulta não encontrada" é impressa na saída
    }

    @Test
    public void testDesmarcarConsulta_ConsultaEncontrada() throws SQLException {
        int consultaId = 1;

        Consulta consulta = new Consulta();
        consulta.setId(consultaId);

        when(consultaDAOMock.buscarConsultaPorId(consulta.getId())).thenReturn(consulta);

        consultaController.desmarcarConsulta(consultaId);

        verify(consultaDAOMock).buscarConsultaPorId(consultaId);
        verify(consultaDAOMock).remarcarConsulta(consulta);
    }

    @Test
    public void testDesmarcarConsulta_ConsultaNaoEncontrada() throws SQLException {
        int consultaId = 1;

        Consulta consulta = new Consulta();
        consulta.setId(consultaId);

        when(consultaDAOMock.buscarConsultaPorId(consulta.getId())).thenReturn(null);

        consultaController.desmarcarConsulta(consultaId);

        verify(consultaDAOMock).buscarConsultaPorId(consultaId);
        verify(consultaDAOMock, never()).remarcarConsulta(consulta);
        // Verifica se a mensagem "Consulta não encontrada" é impressa na saída
    }
}
