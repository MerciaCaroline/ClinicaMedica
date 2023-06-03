package controllers;

import java.util.Date;
import java.util.List;

import models.Consulta;
import models.Medico;
import models.Paciente;
import models.ResultadoExame;

public class MedicoController {
    private static Medico _medico;

    public MedicoController(Medico medico) {
        _medico = medico;
    }

    public void registrarConsulta(Paciente paciente, Date data, String descricao) {
        // Lógica para registrar a consulta
    }

    public void autorizarExame(Paciente paciente, String tipo) {
        // Lógica para autorizar o exame para o paciente
    }

    public List<Consulta> consultarConsultas() {
        // Lógica para consultar as consultas agendadas para o médico
        return null;
    }

    public List<ResultadoExame> consultarResultadosExames() {
        // Lógica para consultar os resultados de exames dos pacientes atendidos pelo médico
        return null;
    }

    public void prescreverMedicamento(Paciente paciente, String medicamento) {
        // Lógica para prescrever um medicamento para o paciente
    }

    public List<Paciente> consultarPacientes() {
        // Lógica para consultar a lista de pacientes atendidos pelo médico
        return null;
    }

    public void adicionarPrescricao(Paciente paciente, String medicamento) {
        // Lógica para adicionar uma prescrição médica ao paciente
    }
}
