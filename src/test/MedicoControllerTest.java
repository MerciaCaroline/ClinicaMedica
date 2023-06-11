package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
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
    }

    @Test
    public void testAutorizarExame() throws SQLException {
    }

    @Test
    public void testAutorizarExameCodigoInvalido() throws SQLException {
    }

    @Test
    public void testRegistrarHistoricoPaciente() throws SQLException {
    }

    @Test
    public void testRegistrarHistoricoPacienteNomeInvalido() throws SQLException {
    }

    @Test
    public void testConsultarResultadosExames() throws SQLException {
    }
}
