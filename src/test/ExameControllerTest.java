package test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import controllers.ExameController;
import dataaccess.ExameDAO;
import models.Exame;
import models.Laboratorio;
import models.Medico;
import models.Paciente;

public class ExameControllerTest {

    private ExameController exameController;

    @Mock
    private ExameDAO exameDAOMock;

    @Mock
    private Connection connectionMock;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        exameController = new ExameController(connectionMock);
        exameController.setExameDAO(exameDAOMock);
    }

    @Test
    public void testSolicitarExame() throws SQLException {
        String tipo = "Exame de Sangue";
        Paciente paciente = new Paciente();
        Medico medico = new Medico();
        Laboratorio laboratorio = mock(Laboratorio.class);

        exameController.solicitarExame(tipo, paciente, medico, laboratorio);

        verify(exameDAOMock).inserir(any(Exame.class));
    }

    @Test
    public void testBuscarExame() throws SQLException {
        int id = 1;
        Exame exame = new Exame();
        when(exameDAOMock.buscarPorId(id)).thenReturn(exame);

        Exame result = exameController.buscarExame(id);

        assertEquals(exame, result);
        verify(exameDAOMock).buscarPorId(id);
    }

    @Test
    public void testBuscarTodosExames() throws SQLException {
        List<Exame> exames = new ArrayList<>();
        exames.add(new Exame());
        exames.add(new Exame());

        when(exameDAOMock.buscarTodos()).thenReturn(exames);

        List<Exame> result = exameController.buscarTodosExames();

        assertEquals(exames, result);
        verify(exameDAOMock).buscarTodos();
    }

    @Test
    public void testObterPendentesPorLaboratorio() throws SQLException {
        int laboratorioId = 1;
        Laboratorio laboratorio = new Laboratorio();
        laboratorio.setId(laboratorioId);

        List<Exame> exames = new ArrayList<>();
        exames.add(new Exame());
        exames.add(new Exame());

        when(exameDAOMock.buscarPorLaboratorio(laboratorioId)).thenReturn(exames);

        List<Exame> result = exameController.obterPendentesPorLaboratorio(laboratorio);

        assertEquals(exames, result);
        verify(exameDAOMock).buscarPorLaboratorio(laboratorioId);
    }

    @Test
    public void testRemoverExame() throws SQLException {
        int id = 1;

        exameController.removerExame(id);

        verify(exameDAOMock).excluir(id);
    }

    @Test
    public void testEnviarResultadoExame_ExamesPendentes() throws SQLException {
        Laboratorio laboratorio = new Laboratorio();
        List<Exame> examesPendentes = new ArrayList<>();
        examesPendentes.add(mock(Exame.class));
        examesPendentes.add(mock(Exame.class));

        when(exameDAOMock.buscarPorLaboratorio(anyInt())).thenReturn(examesPendentes);

        exameController.enviarResultadoExame(laboratorio);

        verify(exameDAOMock, times(2)).inserirResultado(any(Exame.class));
    }

    @Test
    public void testConsultarResultadosExames() throws SQLException {
        int laboratorioId = 1;
        Laboratorio laboratorio = new Laboratorio();
        laboratorio.setId(laboratorioId);

        List<Exame> exames = new ArrayList<>();
        exames.add(mock(Exame.class));
        exames.add(mock(Exame.class));

        when(exameDAOMock.buscarPorLaboratorio(laboratorioId)).thenReturn(exames);

        List<Exame> result = exameController.consultarResultadosExames(laboratorio);

        assertEquals(exames, result);
        verify(exameDAOMock).buscarPorLaboratorio(laboratorioId);
    }

    @Test
    public void testDisponibilizarExameOnline() throws SQLException {
        int codigoExame = 1;
        Exame exame = new Exame();

        when(exameDAOMock.buscarPorId(codigoExame)).thenReturn(exame);

        boolean result = exameController.disponibilizarExameOnline(codigoExame);

        assertTrue(result);
        assertTrue(exame.isDisponivelOnline());
        verify(exameDAOMock).atualizarFlags(anyInt(), anyBoolean(), anyBoolean());
    }

    @Test
    public void testMarcarComoEntregue() throws SQLException {
        Exame exame = new Exame();

        exameController.marcarComoEntregue(exame);

        assertTrue(exame.isEntreguePaciente());
        verify(exameDAOMock).atualizarFlags(anyInt(), anyBoolean(), anyBoolean());
    }
}
