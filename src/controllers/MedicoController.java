package controllers;

import java.sql.Connection;
import java.sql.SQLException;

import dataaccess.MedicoDAO;
import models.Medico;
import models.Paciente;
import models.ResultadoExame;

public class MedicoController {
    public ExameController exameController;
    public ResultadoExameController resultadoExameController;
    public PacienteController pacienteController;
    private MedicoDAO medicoDAO;
    private Connection connection;

     public MedicoController(Connection connection) {
        exameController = new ExameController();
        pacienteController = new PacienteController();
        medicoDAO = new MedicoDAO();
        this.connection = connection;
    }

    public MedicoController() {
        exameController = new ExameController();
        pacienteController = new PacienteController();
        medicoDAO = new MedicoDAO();
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

    public ResultadoExame consultarResultadoExame(int examameId) throws SQLException {
        return resultadoExameController.disponibilizarResultadoExame(examameId);
    }

    public Medico buscarMedicoPorNome(String nome) throws SQLException {
        return medicoDAO.buscarPorNome(nome);
    }

    public Medico buscarMedicoPorId(int id) throws SQLException {
        return medicoDAO.buscarPorId(id);
    }
}
