package controllers;

import java.util.List;

import models.Laboratorio;
import models.Medico;
import models.Paciente;
import models.ResultadoExame;

public class LaboratorioController {
    private static Laboratorio _laboratorio;

    public LaboratorioController(Laboratorio laboratorio) {
        _laboratorio = laboratorio;
    }

    public void enviarResultadoExame(ResultadoExame resultadoExame, Medico medico) {
        // Lógica para enviar o resultado do exame para o médico
    }

    public List<ResultadoExame> consultarResultadosExames() {
        // Lógica para consultar os resultados de exames enviados pelo laboratório
        return null;
    }
    
    public void enviarResultadoExame(ResultadoExame resultadoExame, Medico medico, Paciente paciente) {
        // Lógica para enviar o resultado do exame diretamente ao médico ou ao paciente
    }
}
