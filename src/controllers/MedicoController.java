package controllers;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import models.Consulta;
import models.Medico;
import models.Paciente;
import models.ResultadoExame;

import dataaccess.MedicoDAO;

public class MedicoController {
    private Medico medico;

    public MedicoController(Medico medico) {
        this.medico = medico;
    }


    public void registrarConsulta(Paciente paciente, Date data, String descricao) {
        // Lógica para registrar a consulta
    }

    public void autorizarExameOnline(int codigoExame) {
        // Lógica para autorizar a disponibilidade do exame online
        // ExameController exameController = new ExameController();
        // exameController.autorizarExameOnline(codigoExame);
    }

    public List<Consulta> consultarConsultas() {
        // Lógica para consultar as consultas agendadas para o médico
        return null;
    }

    public List<String> getHistoricoPaciente(String nomePaciente) {
        // Lógica para obter o histórico do paciente pelo nome
        // PacienteController pacienteController = new PacienteController();
        // return pacienteController.consultarHistoricoPaciente(nomePaciente);
        return null;
    }

    public static Medico getMedicoPorNome(String NomeMedico){
        Medico medico = null;
        try {
            medico = MedicoDAO.buscarPorNome(NomeMedico);
        } catch (SQLException e) {
            medico = null;
        }
        return medico;
    }

    public List<ResultadoExame> consultarResultadosExames() {
        // Lógica para consultar os resultados de exames dos pacientes atendidos pelo médico
        return null;
    }
}
