package controllers;

import java.util.Date;
import java.util.List;

import models.Consulta;
import models.Exame;
import models.Medico;
import models.Paciente;
import models.ResultadoExame;

public class PacienteController {
    private static Paciente _paciente;

    public PacienteController(Paciente paciente) {
        _paciente = paciente;
    }

    public void marcarConsulta(Medico medico, Date data, String descricao) {
        // Lógica para agendar a consulta
    }

    public void receberResultadoExame(ResultadoExame resultadoExame) {
        // Lógica para receber o resultado do exame
    }

    public void cancelarConsulta(Consulta consulta) {
        // Lógica para cancelar a consulta
    }

    public List<Consulta> consultarConsultas() {
        // Lógica para consultar as consultas marcadas para o paciente
        return null;
    }

    public List<ResultadoExame> consultarResultadosExames() {
        // Lógica para consultar os resultados de exames do paciente
        return null;
    }

    public List<Exame> solicitarExames(List<String> tiposExame) {
        // Lógica para solicitar exames ao médico ou laboratório
        return null;
    }

    public void autorizarExamesOnline() {
        // Lógica para autorizar que os resultados de exames sejam disponibilizados online
    }
}
