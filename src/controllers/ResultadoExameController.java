package controllers;

import models.ResultadoExame;

public class ResultadoExameController {
    private static ResultadoExame _resultadoExame;

    public ResultadoExameController(ResultadoExame resultadoExame) {
        _resultadoExame = resultadoExame;
    }

    public void disponibilizarOnline() {
        // Lógica para disponibilizar o resultado do exame online
    }

    public void entregarAoPaciente() {
        // Lógica para entregar o resultado do exame diretamente ao paciente
    }

    public void guardarNaClinica() {
        // Lógica para guardar o resultado do exame na clínica para consulta posterior
    }
}
