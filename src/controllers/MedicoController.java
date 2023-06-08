package controllers;

import java.sql.SQLException;
import java.util.List;

import models.Exame;
import models.Paciente;

public class MedicoController {
    ExameController exameController;
    PacienteController pacienteController;

     public MedicoController() {
        exameController = new ExameController();
        pacienteController = new PacienteController();
    }

    public boolean autorizarExame(int codigo) throws SQLException {
        if(exameController.buscarExame(codigo) != null){
            exameController.buscarExame(codigo).setAutorizado(true);
            exameController.atualizarExame(exameController.buscarExame(codigo));

            return true;
        }

        return false;
    }

    public boolean registrarHistoricoPaciente(String nomePaciente, String historicoPaciente) throws SQLException {
        Paciente paciente = pacienteController.buscarPacientePorNome(nomePaciente);
        
        if(paciente != null){
            paciente.setHistoricoPaciente(historicoPaciente);
            pacienteController.atualizarPaciente(paciente);

            return true;
        }

        return false;
    }

    public List<Exame> consultarResultadosExames(int medicoId) throws SQLException {
        return exameController.buscarResultadoExames(medicoId);
    }
}
