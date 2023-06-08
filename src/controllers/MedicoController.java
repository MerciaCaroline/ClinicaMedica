package controllers;

import java.sql.SQLException;
import java.util.List;

import dataaccess.MedicoDAO;
import models.Exame;
import models.Medico;
import models.Paciente;

public class MedicoController {
    public ExameController exameController;
    public PacienteController pacienteController;
    private MedicoDAO medicoDAO;

     public MedicoController() {
        exameController = new ExameController();
        pacienteController = new PacienteController();
        medicoDAO = new MedicoDAO();
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

    public Medico buscarMedicoPorNome(String nome) throws SQLException {
        return medicoDAO.buscarPorNome(nome);
    }
}
